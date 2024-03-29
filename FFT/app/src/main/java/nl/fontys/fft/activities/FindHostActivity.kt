package nl.fontys.fft.activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import nl.fontys.fft.MainActivity
import nl.fontys.fft.R


class FindHostActivity: AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private lateinit var mapFrag: SupportMapFragment
    lateinit var clientLocation: FusedLocationProviderClient

    lateinit var gglMap: GoogleMap

    private lateinit var etCity: EditText
    private lateinit var etDate: EditText
    private lateinit var etGuestNr: EditText
    private lateinit var btnSearch: Button
    private lateinit var botNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_host)

        //Back btn
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        etCity = findViewById(R.id.etCity)
        etDate = findViewById(R.id.etDate)
        etGuestNr = findViewById(R.id.etGuestsNr)
        btnSearch = findViewById(R.id.btnSearch)
        botNav = findViewById(R.id.bnv)

        mapFrag = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment

        mapFrag.getMapAsync(this)

        clientLocation = LocationServices.getFusedLocationProviderClient(this)

        btnSearch.setOnClickListener {
            val intent = Intent(this, HostsResult::class.java)
            val extras = Bundle()
            extras.putString("city", etCity.text.toString())
            extras.putString("date", etDate.text.toString())
            extras.putString("guests_nr", etGuestNr.text.toString())
            intent.putExtras(extras)
            startActivity(intent)
        }

        //Bottom-nav setup
        botNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Respond to navigation item 1 click
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.msg -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.help -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    //When map is ready
    override fun onMapReady(googleMap: GoogleMap) {
        gglMap = googleMap
        if(checkLocationPermission()) {

            gglMap.setMyLocationEnabled(true);
            gglMap.getUiSettings().setMyLocationButtonEnabled(true)

            //Add marker and animate view on long click
            gglMap.setOnMapLongClickListener {
                gglMap.clear()

                gglMap.addMarker(MarkerOptions().position(it).title("Clicked location"))
                var cityName = getCityName(it.latitude, it.longitude)

                var city = Editable.Factory.getInstance().newEditable(cityName)
                etCity.text = city

                gglMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 10F))
            }

            //Get user's current location
            clientLocation.lastLocation.addOnCompleteListener {
                val latitude = it.result?.latitude
                val longitude = it.result?.longitude

                val pos = LatLng(latitude!!, longitude!!)

                gglMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 10F))

            }

            //Add marker and animate camera to chosen city
            etCity.setOnFocusChangeListener { view, b ->
                if (!b && etCity.text.isNotEmpty()) {
                    var pos = getAddressFromName(etCity.text.toString())

                    if (pos != null) {
                        gglMap.clear()
                        gglMap.addMarker(MarkerOptions().position(pos).title("Typed location"))
                        gglMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 10F))
                    }
                }
            }
        }
    }

    //Add marker on long click
    override fun onMapLongClick(p0: LatLng) {
        if(checkLocationPermission()) {
            gglMap.clear()
            gglMap.addMarker(MarkerOptions().position(p0).title("Clicked location"))
        }
    }

    //Remove focus from input fields when a user clicks else where
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action == MotionEvent.ACTION_DOWN){
            var view: View? = currentFocus
            if (view is EditText) {
                var outRect: Rect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.getRawX().toInt(), ev.getRawY().toInt())) {
                    view.clearFocus()
                    var imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    //Get city name for lat and long
    private fun getCityName(lat: Double, long: Double) : String {
        val gcd = Geocoder(this)
        val addresses: List<Address> = gcd.getFromLocation(lat, long, 1)
        if (addresses.isNotEmpty()) {
            return addresses[0].locality
        }
        return "City Name not Found!"
    }

    //Get latitude and longitude from city name
    private fun getAddressFromName(city: String): LatLng? {
        val gcd = Geocoder(this)
        var pos: LatLng? = null
        try {
            val addresses: List<Address> = gcd.getFromLocationName(city, 1)
            var lat = addresses.get(0).latitude
            var long = addresses.get(0).longitude
            pos = LatLng(lat, long)
        } catch (ex: Exception) {
            Log.e("Error", "City Not Found: ${ex}")
        }

        return pos
    }


    private fun checkLocationPermission () : Boolean {
       var state = false

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
           if (this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
               && this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
           ){
               state = true
           } else {
               ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                   android.Manifest.permission.ACCESS_COARSE_LOCATION), 1000)
           }
       } else {
           state = true
       }
       return state
    }
}