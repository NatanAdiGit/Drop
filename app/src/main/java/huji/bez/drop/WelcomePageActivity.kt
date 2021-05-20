package huji.bez.drop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val start: Button = findViewById(R.id.start)

        start.setOnClickListener {
            val intent = Intent(this@WelcomePageActivity, DropInitActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}