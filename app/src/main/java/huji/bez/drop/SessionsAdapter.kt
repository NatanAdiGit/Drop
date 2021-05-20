package huji.bez.drop

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalTime
import java.util.*
import kotlin.collections.HashMap

class SessionsAdapter: RecyclerView.Adapter<sessionItemHolder>() {

    private val sessionsSetsMap: HashMap<String, Day> = hashMapOf(
        "SUNDAY" to Day(),
        "MONDAY" to Day(),
        "TUESDAY" to Day(),
        "WEDNESDAY" to Day(),
        "THURSDAY" to Day(),
        "FRIDAY" to Day(),
        "SATURDAY" to Day()
    )

    private lateinit var currentDay : String

    fun setCurrentDay(currentDay : String) {
        this.currentDay = currentDay

    }

    override fun getItemCount() : Int {
        return sessionsSetsMap[currentDay]!!.size()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): sessionItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.row_session_item, parent, false)
        return sessionItemHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: sessionItemHolder, position: Int) {
        val newSession: Session = Session(
            holder.description.text.toString(),
            LocalTime.parse(holder.startTime.text.toString() + ":00"),
            LocalTime.parse(holder.endTime.text.toString() + ":00")
        )
        sessionsSetsMap[currentDay]!!.addSession(newSession)


        // set the delete button onClick.
        holder.deleteButton.setOnClickListener {
            sessionsSetsMap[currentDay]!!.removeSession(newSession)
        }
    }
}