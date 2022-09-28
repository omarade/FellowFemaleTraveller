package nl.fontys.fft.activities

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import nl.fontys.fft.R

class HostPost : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_post)

        var tvSpots = findViewById<TextView>(R.id.tvSpots)
        var tvCountry = findViewById<TextView>(R.id.tvCountry)
        var tvCity = findViewById<TextView>(R.id.tvCity)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val imagePost = findViewById<ImageView>(R.id.imagePost)

        val bundle1 = intent.extras

        tvSpots?.setText(bundle1!!.getString("numberSpots", "count"))
        tvCountry?.setText(bundle1!!.getString("locationCountry", "country"))
        tvCity?.setText(bundle1!!.getString("locationCity", "city"))
        tvDescription?.setText(bundle1!!.getString("Description", "info"))




    }

}


