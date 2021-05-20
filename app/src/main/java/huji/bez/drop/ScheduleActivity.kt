package huji.bez.drop

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalTime
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    var mDateSetListener: DatePickerDialog.OnDateSetListener? = null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        var currentDay = "SUNDAY"

        val schedule = Schedule()

        val itemsRecycler: RecyclerView = findViewById(R.id.tasks)
        val createSessionButton : FloatingActionButton = findViewById(R.id.addNewSessionButton)
        val editSessionView : EditText = findViewById(R.id.newTask)
        val startTimeButton : Button = findViewById(R.id.startTimeButton)
        val endTimeButton : Button = findViewById(R.id.endTimeButton)
        val sendScheduleButton : Button = findViewById(R.id.sendSchedule)

        var startTime = "12:00"
        var endTime = "13:00"

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
////        }
//
//        var timeSetListener :  TimePickerDialog.OnTimeSetListener? = null
//
//        startTimeButton.setOnClickListener(View.OnClickListener {
//            val cal: Calendar = Calendar.getInstance()
//            val year: Int = cal.get(Calendar.YEAR)
//            val month: Int = cal.get(Calendar.MONTH)
//            val day: Int = cal.get(Calendar.DAY_OF_MONTH)
//            val dialog = DatePickerDialog(
//                this@ScheduleActivity,
//                0,
//                mDateSetListener,
//                year, month, day
//            )
////            val dialog = DatePickerDialog(theme=)
////            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
////            dialog.window!!.setContentView(ColorDrawable(Color.TRANSPARENT))
//            dialog.show()
//        })
//        mDateSetListener =
//            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
//                var month = month
//                month += 1
//                Log.d("TIMETAG", "onDateSet: dd/mm/yyy: $day/$month/$year")
//                val date = "$day/$month/$year"
//                startTimeButton.text = date
//            }


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

        startTimeButton.setOnClickListener {

        }

        endTimeButton.setOnClickListener {

        }

        val adapter = SessionsAdapter()
        adapter.setItems(schedule.getSessionsFromDay(currentDay))

        createSessionButton.setOnClickListener{

            val description:String = editSessionView.text.toString()
            var newSession:Session = Session(
                description,
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
            )

            Log.d("the_session", newSession.description + " " + newSession.startTime.toString() )

            schedule.addSessionToDay(currentDay, newSession)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))

            // define the fun for clicking on the delete button.
            adapter.onDeleteClickCallback = { session ->
                AlertDialog.Builder(this@ScheduleActivity)
                    .setTitle("Are you sure you want to delete:")
                    .setMessage(session.description)
                    .setPositiveButton("YES") { _, _ ->
                        schedule.removeSessionFromDay(currentDay, session)
                        adapter.setItems(schedule.getSessionsFromDay(currentDay))
                    }
                    .setNegativeButton("NO", null)
                    .show()
            }

        }

        itemsRecycler.adapter = adapter
        itemsRecycler.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false /*reverseLayout*/)

        sendScheduleButton.setOnClickListener {

            val scheduleIntent = Intent()
            scheduleIntent.action = "schedule"
            scheduleIntent.putExtra("scheduleIntent", schedule)
            sendBroadcast(scheduleIntent)
            finish()
        }






    }





}