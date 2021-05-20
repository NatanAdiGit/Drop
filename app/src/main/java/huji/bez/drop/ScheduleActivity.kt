package huji.bez.drop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val createSessionButton : FloatingActionButton = findViewById(R.id.addNewSessionButton)
        val editSessionView : EditText = findViewById(R.id.newTask)
        val startTimeButton : Button = findViewById(R.id.startTimeButton)
        val endTimeButton : Button = findViewById(R.id.endTimeButton)
        var startTime:String
        var endTime :String

        //todo : HH:MM

        startTimeButton.setOnClickListener {

        }

        endTimeButton.setOnClickListener {

        }






    }





}