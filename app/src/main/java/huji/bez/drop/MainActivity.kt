package huji.bez.drop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

//    private val userData = UserData()
    private var schedule = Schedule()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val droplingSprite: DroplingSprite = DroplingSprite(this)
        droplingSprite.showIdleState()

//        if (mainSchedule.getSchedule().isEmpty()) {
//
//            val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
//            startActivity(intent)
//
//        }

        val userData = UserData("Keren", "Momo")

//        while (true) {
//
//            onUserInteraction()
//            onUserLeaveHint()
//
//        }

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