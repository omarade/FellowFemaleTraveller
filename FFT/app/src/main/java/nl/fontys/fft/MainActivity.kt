package nl.fontys.fft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import nl.fontys.fft.activities.GuestChoiceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnHost: Button
    private lateinit var btnGuest: Button
    private lateinit var botNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHost = findViewById(R.id.btnHost)
        btnGuest = findViewById(R.id.btnGuest)
        botNav = findViewById(R.id.bnv)

        btnGuest.setOnClickListener {
            val intent = Intent(this, GuestChoiceActivity::class.java)
            startActivity(intent)
        }


        botNav.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.home -> {
                    // Respond to navigation item 1 click
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