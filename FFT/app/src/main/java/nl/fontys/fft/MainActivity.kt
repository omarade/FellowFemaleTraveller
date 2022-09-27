package nl.fontys.fft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import nl.fontys.fft.activities.GuestChoiceActivity
import nl.fontys.fft.activities.HostForm1

class MainActivity : AppCompatActivity() {

    private lateinit var btnHost: Button
    private lateinit var btnGuest: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnHost = findViewById(R.id.btnHost)
        btnGuest = findViewById(R.id.btnGuest)



        btnGuest.setOnClickListener {
            val intent = Intent(this, GuestChoiceActivity::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
        }

        btnHost.setOnClickListener {
            val intent = Intent(this, HostForm1::class.java)
            intent.putExtra("key", "value")
            startActivity(intent)
        }

    }
}