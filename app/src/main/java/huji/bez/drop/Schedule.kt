package huji.bez.drop

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate

class Schedule : Serializable {

    private val daysMap : HashMap<String,Day> = hashMapOf(
        "SUNDAY" to Day(),
        "MONDAY" to Day(),
        "TUESDAY" to Day(),
        "WEDNESDAY" to Day(),
        "THURSDAY" to Day(),
        "FRIDAY" to Day(),
        "SATURDAY" to Day()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun isBlocking() : Boolean? {
        val currentDay : String = LocalDate.now().dayOfWeek.toString()
        return daysMap[currentDay]?.isBlocking()
    }

    fun setDay(dayName : String, day : Day) {
        daysMap[dayName] = day
    }
}