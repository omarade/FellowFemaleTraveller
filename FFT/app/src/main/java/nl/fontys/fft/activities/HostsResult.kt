package nl.fontys.fft.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.fontys.fft.MainActivity
import nl.fontys.fft.R
import nl.fontys.fft.data.UserViewModel
import nl.fontys.fft.models.User

class HostsResult : AppCompatActivity() {

    private lateinit var users: MutableList<User>

    private lateinit var lvHosts: ListView
    lateinit var city: String
    lateinit var date: String
    lateinit var guestsNr: String
    private lateinit var botNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosts_result)

        //Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        botNav = findViewById(R.id.bnv)
        lvHosts = findViewById(R.id.lvHosts)

        val extras = intent.extras
        city = extras?.getString("city").toString()
        date = extras?.getString("date").toString()
        guestsNr = extras?.getString("guestsNr").toString()

        val userViewModel = UserViewModel()
        users = userViewModel.getUsersByCity(city)

        val adapter = ArrayAdapter(this, R.layout.host_list_elem, R.id.tvElement, users)
        lvHosts.adapter = adapter

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
}