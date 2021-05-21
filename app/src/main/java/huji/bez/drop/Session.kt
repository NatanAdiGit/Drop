package huji.bez.drop
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalTime

class Session(var description : String, var startTime : LocalTime, var endTime: LocalTime) : Serializable,
    Comparable<Session> {

    override fun compareTo(other: Session): Int {
        return startTime.compareTo(other.startTime)
    }

    fun setSession(newDescription : String, newStartTime : LocalTime,  newEndTime :LocalTime) {
        description = newDescription
        startTime = newStartTime
        endTime = newEndTime
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun isBlocking() : Boolean {
        val currentTime : LocalTime = LocalTime.now()
        Log.e("is_block_session",((currentTime >= startTime) && (currentTime <= endTime)).toString())
        return (currentTime >= startTime) && (currentTime <= endTime)
    }


}