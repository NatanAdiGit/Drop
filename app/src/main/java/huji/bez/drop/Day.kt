package huji.bez.drop
import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalTime
import java.util.*

class Day : Serializable{

    private val sessionsSet : TreeSet<Session> = sortedSetOf()

    fun size() : Int {
        return sessionsSet.size
    }

    fun isEmpty() : Boolean {
        return sessionsSet.isEmpty()
    }

    fun addSession(newSession : Session) {
        sessionsSet.add(newSession)
    }

    fun setSession(session: Session, description : String, newFrom : LocalTime,  newTo :LocalTime) {
        sessionsSet.find { other -> session.description == other.description}
            ?.setSession(description, newFrom,  newTo)
    }

    fun removeSession(newSession : Session) {
        sessionsSet.remove(newSession)
    }

    fun getSet(): TreeSet<Session> {
        return sessionsSet
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun isBlocking() : Boolean{
        for (session in sessionsSet){
            if (session.isBlocking())
                return true
        }
        return false
    }
}