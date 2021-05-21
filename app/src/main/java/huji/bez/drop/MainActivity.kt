package huji.bez.drop

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {


    private var schedule = Schedule()

    private var pressedTimeBackButton: Long = 0

    private var isNotFocused = false

    private var isOnBlockedMode = false

    private var startCountingTenMin: Long = 0

    private val MIN_LOVE_LEVEL = 50

    private val MIN_HUNGRY_LEVE = 50

    private val MIN_ENERGY_LEVE = 50

    private val energyProgBar :ProgressBar = findViewById(R.id.progressBarEnergy)

    private val loveProgBar :ProgressBar = findViewById(R.id.progressBarHeart)

    private val hungryProgBar :ProgressBar = findViewById(R.id.progressBarWater)

    private val userData = UserData("Keren", "Momo")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        Log.d("CREATEMAINTAG", "WHYYYYYYYYYYYYYYYYYYYYYY")

//        val droplingSprite: DroplingSprite = DroplingSprite(this)
//        droplingSprite.showIdleState()

        if (savedInstanceState == null) {

            val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
            startActivity(intent)


        }
//        val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
//        startActivity(intent)


        val handler = Handler()
        val delay: Long = 1000 // 1000 milliseconds == 1 second

        val bubble: ImageView = findViewById(R.id.imageView5)

        startCountingTenMin = System.currentTimeMillis()
//        handler.postDelayed(object : Runnable {
//            override fun run() {
//                println("myHandler: here!") // Do your work here
//                if (bubble.visibility == View.GONE) {
//                    bubble.visibility = View.VISIBLE
//                } else {
//                    bubble.visibility = View.GONE
//                }
//                handler.postDelayed(this, delay)
//            }
//        }, delay)
        handler.postDelayed(object : Runnable {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun run() {
                if (runOnRepeat())
                    return
                println("myHandler: here!") // Do your work here
                if (bubble.visibility == View.GONE) {
                    bubble.visibility = View.VISIBLE
                } else {
                    bubble.visibility = View.GONE
                }
                handler.postDelayed(this, delay)
            }
        }, delay)
    }


    override fun onBackPressed() {
        var currentTime = System.currentTimeMillis()
        while (System.currentTimeMillis() - currentTime < 50)
            if (pressedTimeBackButton + 2000 > System.currentTimeMillis()) {
                super.onBackPressed();
                isNotFocused = true
            } else {
                Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show();
            }
        pressedTimeBackButton = System.currentTimeMillis();
        Log.e("not_focuse_is", isNotFocused.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!MainSchedule.getSchedule().isEmpty()) {
            isNotFocused = true
            Log.e("not_focuse_is", isNotFocused.toString())
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun runOnRepeat() : Boolean {
        Log.e("is_not_focuse",isNotFocused.toString())
        var isScheduleIsBlockingNow = MainSchedule.getSchedule().isBlocking()
        if (isScheduleIsBlockingNow && isNotFocused) {
            Log.e("I_entered",(isScheduleIsBlockingNow && isNotFocused).toString())
            userData.energyLevel -= 30
            Log.e("ennnnnergy",userData.energyLevel.toString())
            if (userData.energyLevel < 0) {
                userData.energyLevel = 0
            }
            userData.loveLevel -= 10
            if (userData.loveLevel < 0) {
                userData.loveLevel = 0
            }

            userData.hungerLevel -= 15
            if (userData.hungerLevel < 0) {
                userData.hungerLevel = 0
            }
            energyProgBar.progress = userData.energyLevel
            loveProgBar.progress = userData.loveLevel
            hungryProgBar.progress = userData.hungerLevel
            return true
        }

        if (isScheduleIsBlockingNow && !isNotFocused) {
            if (System.currentTimeMillis() - startCountingTenMin > 600000) {
                userData.energyLevel += 5
                userData.hungerLevel += 3
                energyProgBar.progress = userData.energyLevel
                hungryProgBar.progress = userData.hungerLevel
            }
        }

//        if (userData.energyLevel < MIN_ENERGY_LEVE)
//        // todo - call low level function
//
//        else if (userData.loveLevel < MIN_LOVE_LEVEL)
//        // todo - call low level function
//
//        else if (userData.hungerLevel < MIN_HUNGRY_LEVE)
//        // todo - call low level function

        return false
    }
}

object MainSchedule {
    private var schedule = Schedule()

    fun getSchedule(): Schedule {
        return schedule
    }

    fun setSchedule(newSchedule: Schedule) {
        schedule = newSchedule
    }
}

object DropName {
    private lateinit var dropName: String

    fun getDropName(): String {
        return dropName
    }

    fun setDropName(name: String) {
        dropName = name
    }


}