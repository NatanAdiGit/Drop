package huji.bez.drop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private var userInteractionTime : Long = 0;
    private var isNotFocused = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        if (mainSchedule.getSchedule().isEmpty()) {
            val sced: Button = findViewById(R.id.next_button)
            val init: Button = findViewById(R.id.init)

            init.setOnClickListener {
                val intent = Intent(this@MainActivity, DropInitActivity::class.java)
                startActivity(intent)
            }

            sced.setOnClickListener {
                val intent = Intent(this@MainActivity, ScheduleActivity::class.java)
                startActivity(intent)
            }
        }

        val userData = UserData("Keren", "Momo")

        while (true) {

            onUserInteraction()
            onUserLeaveHint()



        }


    }

    override fun onUserInteraction() {
        userInteractionTime = System.currentTimeMillis();
        super.onUserInteraction();
        Log.i("appname", "Interaction"); }

    override fun onUserLeaveHint() {
        var uiDelta :Long = (System.currentTimeMillis() - userInteractionTime);
        super.onUserLeaveHint();

        isNotFocused = uiDelta < 100 // if under 100 then user pressed homescreen
    }

object mainSchedule {
    private var schedule = Schedule()

    fun getSchedule():Schedule {return schedule}
    fun setSchedule(newSchedule: Schedule) {
        schedule = newSchedule
    }
}