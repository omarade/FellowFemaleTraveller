package nl.fontys.fft.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

    var num = 0;
    



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

        //database = FirebaseDatabase.getInstance().getReference("Ads")

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
            startActivity(intent)

            val spots = textViewNumber.text.toString()
            val country = textCountry.text.toString()
            val city = textCity.text.toString()
            val description = textDescription.text.toString()

            saveFireStore(spots, country, city, description)

        }
        
        
        //ADD IMAGE

//        addImg.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, 123)
//        }
        


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

}