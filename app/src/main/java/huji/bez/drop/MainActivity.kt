package huji.bez.drop

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {


    private var schedule = Schedule()

    private var pressedTimeBackButton: Long = 0

    private var isNotFocused = false

    private var isOnBlockedMode = false

    private var startCountingTenMin: Long = 0

    private val MIN_LOVE_LEVEL = 50

    private val MIN_HUNGRY_LEVE = 50

    private val MIN_ENERGY_LEVE = 50

    private lateinit var energyProgBar : ProgressBar

    private lateinit var loveProgBar : ProgressBar

    private lateinit var hungryProgBar : ProgressBar

    lateinit var droplingSprite : DroplingSprite


    val userData = UserData("Keren", "Momo")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        energyProgBar  = findViewById(R.id.progressBarEnergy)
        loveProgBar  = findViewById(R.id.progressBarHeart)
        hungryProgBar = findViewById(R.id.progressBarWater)

        energyProgBar.progress = DropUser.getDropUser().energyLevel
        loveProgBar.progress = DropUser.getDropUser().loveLevel
        hungryProgBar.progress = DropUser.getDropUser().hungerLevel


        if (savedInstanceState == null) {
            val intent = Intent(this@MainActivity, WelcomePageActivity::class.java)
            startActivity(intent)
        }

        droplingSprite = DroplingSprite(this)
        droplingSprite.setBodyColor(Color.parseColor(DropUser.getDropUser().color))
        droplingSprite.showIdleState()

        val handler = Handler()
        val delay: Long = 1000 // 1000 milliseconds == 1 second

        val bubble: ImageView = findViewById(R.id.imageView5)

        startCountingTenMin = System.currentTimeMillis()
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
            DropUser.getDropUser().loveLevel -= 10
            if (DropUser.getDropUser().loveLevel < 0) {
                DropUser.getDropUser().loveLevel = 0
            }

            DropUser.getDropUser().hungerLevel -= 15
            if (DropUser.getDropUser().hungerLevel < 0) {
                DropUser.getDropUser().hungerLevel = 0
            }
            energyProgBar.progress = DropUser.getDropUser().energyLevel
            loveProgBar.progress = DropUser.getDropUser().loveLevel
            hungryProgBar.progress = DropUser.getDropUser().hungerLevel
            return true
        }

        if (isScheduleIsBlockingNow && !isNotFocused) {
            if (System.currentTimeMillis() - startCountingTenMin > 600000) {
                DropUser.getDropUser().energyLevel += 5
                DropUser.getDropUser().hungerLevel += 3
                energyProgBar.progress = DropUser.getDropUser().energyLevel
                hungryProgBar.progress = DropUser.getDropUser().hungerLevel
            }
        }

        DropUser.getDropUser().energyLevel = 20
//
//        if (userData.energyLevel < MIN_ENERGY_LEVE)
//            droplingSprite.showSadState() // todo
//
//        else if (userData.loveLevel < MIN_LOVE_LEVEL)
//            droplingSprite.showSadState() // todo
//
//        else if (userData.hungerLevel < MIN_HUNGRY_LEVE)
//            droplingSprite.showSadState() // todo

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

object DropUser {
    private var dropUser: UserData = UserData("user")

    fun getDropUser(): UserData {
        return dropUser
    }

    fun setDropName(name: String) {
        dropUser.dropName = name
    }

    fun setDropColor(color: String) {
        dropUser.color = color
    }




}