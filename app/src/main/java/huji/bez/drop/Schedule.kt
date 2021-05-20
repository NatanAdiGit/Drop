package huji.bez.drop

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

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

    fun isEmpty() : Boolean {
        for ((key, value) in daysMap)
            if (!value.isEmpty())
                return false
        return true
    }
    
    fun setDay(dayName : String, day : Day) {
        daysMap[dayName] = day
    }

    fun getSessionsFromDay(dayName : String) : TreeSet<Session> {
        return daysMap[dayName]!!.getSet()
    }

    fun addSessionToDay(dayName : String, session : Session) {
        daysMap[dayName]!!.addSession(session)
    }

    fun removeSessionFromDay(dayName : String, session : Session) {
        daysMap[dayName]!!.removeSession(session)
    }

}