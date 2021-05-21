package huji.bez.drop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class DropInitActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drop_init)

        val colorButton1: View = findViewById(R.id.color1)
        val colorButton2: View = findViewById(R.id.color2)
        val colorButton3: View = findViewById(R.id.color3)
        val colorButton4: View = findViewById(R.id.color4)

        var nameText : EditText = findViewById(R.id.nameEdit)

        val avatar: ImageView = findViewById(R.id.avatar)
        colorButton1.setOnClickListener {
            DropUser.setDropColor("#CCF1FF")
        }

        colorButton2.setOnClickListener {
            DropUser.setDropColor("#E0D7FF")
        }

        colorButton3.setOnClickListener {
            DropUser.setDropColor("#FFCCE1")
        }

        colorButton4.setOnClickListener {
            DropUser.setDropColor("#FAFFC7")
        }


        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val intent = Intent(this@DropInitActivity, ScheduleActivity::class.java)
            startActivity(intent)
            DropName.setDropName(nameText.text.toString())
            Log.e("DROPTAG", DropName.getDropName())
            finish()
        }
    }
}