package huji.bez.drop

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class sessionItemHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val description : TextView = view.findViewById(R.id.sessionDescription)
    val startTime : TextView = view.findViewById(R.id.startTimeView)
    val endTime : TextView = view.findViewById(R.id.finishTimeView)
    val deleteButton : ImageButton = view.findViewById(R.id.deleteButton)

}