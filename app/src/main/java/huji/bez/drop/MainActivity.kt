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

class MainActivity : AppCompatActivity() {

//    private val userData = UserData()
    private var schedule = Schedule()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val scheduleIntent = Intent(this@MainActivity, ScheduleActivity::class.java)
        startActivity(scheduleIntent)


        val broadcastReceiverForSuccess =  object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent == null || intent.action != "schedule") {
                    Log.e("schedule_not_received", "schedule was not received")
                    return
                }
                schedule = intent.getSerializableExtra("schedule_object") as Schedule
            }

        }

        registerReceiver(broadcastReceiverForSuccess, IntentFilter("schedule"))

//        if (!schedule.isEmpty()) {
//            AlertDialog.Builder(this@MainActivity)
//                .setTitle("")
//                .setMessage("NOT NULL")
//                .setPositiveButton("YES", null)
//                .setNegativeButton("NO", null)
//                .show()
//        }


//        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
//        registerReceiver(broadcastReceiverForSuccess, filter)






//        val sced : Button = findViewById(R.id.next_button)
//        val init : Button = findViewById(R.id.init)
//
//        init.setOnClickListener {
//            val intent = Intent(this@MainActivity, DropInitActivity::class.java)
//            startActivity(intent)
//        }
//
//        sced.setOnClickListener {
//            val intent = Intent(this@MainActivity, ScheduleActivity::class.java)
//            startActivity(intent)
//        }
    }
}