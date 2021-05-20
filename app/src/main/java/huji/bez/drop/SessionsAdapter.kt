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

    private val sessionsSets : TreeSet<Session> = sortedSetOf()

    var onDeleteClickCallback : ((Session) -> Unit)?= null


    fun setItems(items : TreeSet<Session>) {
        sessionsSets.clear()
        sessionsSets.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() : Int {
        return sessionsSets.size
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
        sessionsSets.add(newSession)


        // set the delete button onClick.
        holder.deleteButton.setOnClickListener {
            val callback = onDeleteClickCallback ?: return@setOnClickListener
            callback(newSession)
        }
    }
}