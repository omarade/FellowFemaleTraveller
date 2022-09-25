package nl.fontys.fft.activities

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import nl.fontys.fft.R

class FindHostActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapFrag: SupportMapFragment
    lateinit var clientLocation : FusedLocationProviderClient

    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_host)

        mapFrag = supportFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment

        mapFrag.getMapAsync(this)

        clientLocation = LocationServices.getFusedLocationProviderClient(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {

        val map = googleMap

        if(checkLocationPermission()) {
            clientLocation.lastLocation.addOnCompleteListener {
                val latitude = it.result?.latitude
                val longitude = it.result?.longitude

                val pos = LatLng(latitude!!, longitude!!)

                map.addMarker(MarkerOptions().position(pos).title("Me"))

                Toast.makeText(this, "Position: ${pos}", Toast.LENGTH_LONG)
            }
        }
    }

    fun checkLocationPermission () : Boolean {
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