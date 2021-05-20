package huji.bez.drop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val userData = UserData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (userData.userName == null) {

        }



        val sced : Button = findViewById(R.id.next_button)
        val init : Button = findViewById(R.id.init)

        init.setOnClickListener {
            val intent = Intent(this@MainActivity, DropInitActivity::class.java)
            startActivity(intent)
        }

        sced.setOnClickListener {
            val intent = Intent(this@MainActivity, ScheduleActivity::class.java)
            startActivity(intent)
        }
    }
}