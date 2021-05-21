package huji.bez.drop

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TimerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        val timer: TextView = findViewById(R.id.timer)

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timer.text = "seconds remaining: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timer.text = "done!"
            }
        }.start()
    }
}