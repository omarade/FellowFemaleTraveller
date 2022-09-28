package nl.fontys.fft.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.fontys.fft.MainActivity
import nl.fontys.fft.R

class GuestChoiceActivity : AppCompatActivity() {

    private lateinit var btnFindHost: Button
    private lateinit var btnMeet: Button
    private lateinit var btnHelp: Button
    private lateinit var botNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_choice)

        //Back btn
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        btnFindHost = findViewById(R.id.btnFindHost)
        btnMeet = findViewById(R.id.btnMeet)
        btnHelp = findViewById(R.id.btnHelp)
        botNav = findViewById(R.id.bnv)

        btnFindHost.setOnClickListener {
            val intent = Intent(this, FindHostActivity::class.java)
            intent.putExtra("type", "host")
            startActivity(intent)
        }

        btnMeet.setOnClickListener {
            val intent = Intent(this, FindHostActivity::class.java)
            intent.putExtra("type", "meet")
            startActivity(intent)
        }

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


}