package huji.bez.drop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class DropInitActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drop_init)

        val colorButton1: View = findViewById(R.id.color1)
        val colorButton2: View = findViewById(R.id.color2)
        val colorButton3: View = findViewById(R.id.color3)
        val colorButton4: View = findViewById(R.id.color4)

        val avatar: ImageView = findViewById(R.id.avatar)

        val nextButton: Button = findViewById(R.id.next_button)

        colorButton1.setOnClickListener() {
            avatar.setImageDrawable(getDrawable(R.drawable.avatar_color1))
        }

        colorButton2.setOnClickListener() {
            avatar.setImageDrawable(getDrawable(R.drawable.avatar_color2))
        }

        colorButton3.setOnClickListener() {
            avatar.setImageDrawable(getDrawable(R.drawable.avatar_color3))
        }

        colorButton4.setOnClickListener() {
            avatar.setImageDrawable(getDrawable(R.drawable.avatar_color4))
        }

        nextButton.setOnClickListener {
            val intent = Intent(this@DropInitActivity, ScheduleActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}