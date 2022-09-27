package nl.fontys.fft.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import nl.fontys.fft.R

class GuestChoiceActivity : AppCompatActivity() {

    private lateinit var btnFindHost: Button
    private lateinit var btnMeet: Button
    private lateinit var btnHelp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_choice)

        btnFindHost = findViewById(R.id.btnFindHost)
        btnMeet = findViewById(R.id.btnMeet)
        btnHelp = findViewById(R.id.btnHelp)

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
    }


}