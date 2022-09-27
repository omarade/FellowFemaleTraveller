package nl.fontys.fft.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import nl.fontys.fft.R
import nl.fontys.fft.data.UserViewModel
import nl.fontys.fft.models.User

class HostsResult : AppCompatActivity() {

    private lateinit var users: MutableList<User>

    private lateinit var lvHosts: ListView
    lateinit var city: String
    lateinit var date: String
    lateinit var guestsNr: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hosts_result)



        lvHosts = findViewById(R.id.lvHosts)

        val extras = intent.extras
        city = extras?.getString("city").toString()
        date = extras?.getString("date").toString()
        guestsNr = extras?.getString("guestsNr").toString()

        val userViewModel = UserViewModel()
        users = userViewModel.getUsersByCity(city)

        val adapter = ArrayAdapter(this, R.layout.host_list_elem, R.id.tvElement, users)
        lvHosts.adapter = adapter
    }
}