package nl.fontys.fft.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import nl.fontys.fft.MainActivity
import nl.fontys.fft.R
import nl.fontys.fft.databinding.ActivityMainBinding
import java.lang.IllegalStateException


class HostForm1 : AppCompatActivity() {

    var num = 0
    private val CAMERA_PERMISSION_CODE=134
    private val STORAGE_PERMISSION_CODE=345
    //val imgMap = findViewById<ImageView>(R.id.imgMap)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_form1)



        //lateinit var binding: ActivityMainBinding
        //lateinit var database: DatabaseReference


        //INTENTS FOR HOST POST


        var buttonPlus = findViewById<Button>(R.id.buttonPlus)
        var textViewNumber = findViewById<TextView>(R.id.textViewNumber)
        var buttonMinus = findViewById<Button>(R.id.buttonMinus)
        var btnBackHost = findViewById<Button>(R.id.btnBackHost)
        var btnNextHost = findViewById<Button>(R.id.btnNextHost)
        var textCountry =findViewById<TextInputEditText>(R.id.textCountryField)
        var textDescription = findViewById<TextInputEditText>(R.id.textDescriptionField)
        var textCity = findViewById<TextInputEditText>(R.id.textCityField)
        var addImg = findViewById<Button>(R.id.addImg)
        var galleryImg = findViewById<Button>(R.id.galleryImg)



        //database = FirebaseDatabase.getInstance().getReference("Ads")




        //OPEN CAMERA/GALLERY

        addImg.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        galleryImg.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 456)
        }





        //ASK PERMISSION

//        addImg.setOnClickListener {
//            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
//        }
//
//       galleryImg.setOnClickListener {
//            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE)
//        }





        btnBackHost.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //INTENT  - BUNDLE - VALUES (key, value)

        btnNextHost.setOnClickListener{
            val intent = Intent(this, HostPost::class.java)
            val bundle1 = Bundle()
            bundle1.putString("numberSpots", textViewNumber.text.toString())
            bundle1.putString("locationCountry", textCountry.text.toString())
            bundle1.putString("locationCity", textCity.text.toString())
            bundle1.putString("Description", textDescription.text.toString())
            intent.putExtras(bundle1)
            intent.putExtras(intent)
            startActivity(intent)

            val spots = textViewNumber.text.toString()
            val country = textCountry.text.toString()
            val city = textCity.text.toString()
            val description = textDescription.text.toString()

            saveFireStore(spots, country, city, description)

        }


        //COUNTER


        buttonPlus.setOnClickListener{
            num++
            textViewNumber.text = num.toString()


        }

        buttonMinus.setOnClickListener{

            num--

            textViewNumber.text = num.toString()


        }
    }


    //val imgMap = findViewById<ImageView>(R.id.imgMap)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {

            var bmp : Bitmap = data?.extras?.get("data") as Bitmap
            val imgMap = findViewById<ImageView>(R.id.imgMap)
            imgMap.setImageBitmap(bmp)
        }
        else if (requestCode == 456){

            val imgMap = findViewById<ImageView>(R.id.imgMap)
            imgMap.setImageURI(data?.data)
        }
    }






    //DATABASE

    fun saveFireStore (numberSpots: String, locationCountry: String, locationCity: String, Descritpion: String) {
        val database = FirebaseFirestore.getInstance()
        val ads: MutableMap<String, Any> = HashMap()
        ads["spots"] = numberSpots
        ads["country"] = locationCountry
        ads["city"] = locationCity
        ads["description"] = Descritpion

        database.collection("Ads")
            .add(ads)
            .addOnSuccessListener {
                Toast.makeText(this, "Ad created", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to create ad", Toast.LENGTH_SHORT).show()
            }

    }




    //CAMERA PERMISSION AND ACCESS




//    private fun checkPermission (permission:String, requestCode:Int) {
//        if (ContextCompat.checkSelfPermission(this, permission)==PackageManager.PERMISSION_DENIED)
//
//            //ASK FOR PERMISSION CAMERA
//
//            ActivityCompat.requestPermissions(this, arrayOf(permission),requestCode)
//
//
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if(requestCode == CAMERA_PERMISSION_CODE){
//
//            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
//            }
//        }
//        else if (requestCode == STORAGE_PERMISSION_CODE){
//
//            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//    }
}