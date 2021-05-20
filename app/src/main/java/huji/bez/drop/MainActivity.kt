package huji.bez.drop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    //    private val userData = UserData()
    private var schedule = Schedule()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Log.d("CREATEMAINTAG", "WHYYYYYYYYYYYYYYYYYYYYYY")

//        val droplingSprite: DroplingSprite = DroplingSprite(this)
//        droplingSprite.showIdleState()

//        if (mainSchedule.getSchedule().isEmpty()) {
        if (savedInstanceState == null) {
            
            val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
            startActivity(intent)
        }
//        val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
//        startActivity(intent)


        val userData = UserData("Keren", "Momo")

        val handler = Handler()
        val delay: Long = 1000 // 1000 milliseconds == 1 second

        val bubble: ImageView = findViewById(R.id.imageView5)

        handler.postDelayed(object : Runnable {
            override fun run() {
                println("myHandler: here!") // Do your work here
                if (bubble.visibility == View.GONE) {
                    bubble.visibility = View.VISIBLE
                } else {
                    bubble.visibility = View.GONE
                }
                handler.postDelayed(this, delay)
            }
        }, delay)

//        while (true) {
//
//            onUserInteraction()
//            onUserLeaveHint()
//
//        }

    }

//    override fun onUserInteraction() {
//        userInteractionTime = System.currentTimeMillis();
//        super.onUserInteraction();
//        Log.i("appname", "Interaction"); }
//
//    override fun onUserLeaveHint() {
//        var uiDelta :Long = (System.currentTimeMillis() - userInteractionTime);
//        super.onUserLeaveHint();
//
//        isNotFocused = uiDelta < 100 // if under 100 then user pressed homescreen
//    }
}

object mainSchedule {
    private var schedule = Schedule()

    fun getSchedule(): Schedule {
        return schedule
    }

    fun setSchedule(newSchedule: Schedule) {
        schedule = newSchedule
    }
}