package huji.bez.drop

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    var mDateSetListener: DatePickerDialog.OnDateSetListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val createSessionButton: FloatingActionButton = findViewById(R.id.addNewSessionButton)
        val editSessionView: EditText = findViewById(R.id.newTask)
        val startTimeButton: Button = findViewById(R.id.startTimeButton)
        val endTimeButton: Button = findViewById(R.id.endTimeButton)
        var startTime: String
        var endTime: String

//        var timeSetListener :  OnTimeSetListener? = null
//
//        startTimeButton.setOnClickListener(View.OnClickListener {
//            val cal: Calendar = Calendar.getInstance()
//            val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
//            val minutes: Int = cal.get(Calendar.MINUTE)
//            Log.d("TIMETAG", "onDateSet: dd/mm/yyy: $hour:$minutes")
//            val dialog = TimePickerDialog(
//                this@ScheduleActivity,
//                0, timeSetListener,
//                hour, minutes, false
//            )
//            dialog.show()
//        })
//
//        timeSetListener = OnTimeSetListener { timePicker, hour, minute ->
//            Log.d("TIMETAG", "onDateSet: dd/mm/yyy: $hour:$minute")
//
//            val time = "$hour:$minute"
//            startTimeButton.text = time
//        }

        var timeSetListener :  OnTimeSetListener? = null

        startTimeButton.setOnClickListener(View.OnClickListener {
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)
            val dialog = DatePickerDialog(
                this@ScheduleActivity,
                0,
                mDateSetListener,
                year, month, day
            )
//            val dialog = DatePickerDialog(theme=)
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            dialog.window!!.setContentView(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })
        mDateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month += 1
                Log.d("TIMETAG", "onDateSet: dd/mm/yyy: $day/$month/$year")
                val date = "$day/$month/$year"
                startTimeButton.text = date
            }


/*
        mDateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month += 1
                Log.d(TAG, "onDateSet: dd/mm/yyy: $day/$month/$year")
                val date = "$day/$month/$year"
                mDisplayDate!!.text = date
            }
 */
        //todo : HH:MM

        startTimeButton.setOnClickListener() {

        }

        endTimeButton.setOnClickListener() {

        }


    }


}