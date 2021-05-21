package huji.bez.drop

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

class ScheduleActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val createSessionButton: Button = findViewById(R.id.addNewSessionButton)
        val editSessionView: EditText = findViewById(R.id.newTask)
        val endTimeButton: Button = findViewById(R.id.endTimeButton)
        val startTimeButton: Button = findViewById(R.id.startTimeButton)
        val sendScheduleButton: Button = findViewById(R.id.sendSchedule)

        var startTime: String = startTimeButton.text.toString()
        var endTime: String = endTimeButton.text.toString()
        var currentDay = "SUNDAY"
        val adapter = SessionsAdapter()

        val schedule = Schedule()

        val itemsRecycler: RecyclerView = findViewById(R.id.tasks)

// --------------------------------------------------------------

        val sundayButton: Button = findViewById(R.id.sunday)
        val mondayButton: Button = findViewById(R.id.monday)
        val tuesdayButton: Button = findViewById(R.id.tuesday)
        val wednesdayButton: Button = findViewById(R.id.wednesday)
        val thursdayButton: Button = findViewById(R.id.thursday)
        val fridayButton: Button = findViewById(R.id.friday)
        val saturdayButton: Button = findViewById(R.id.saturday)

        fun enableAllDays() {
            sundayButton.isEnabled = true
            mondayButton.isEnabled = true
            tuesdayButton.isEnabled = true
            wednesdayButton.isEnabled = true
            thursdayButton.isEnabled = true
            fridayButton.isEnabled = true
            saturdayButton.isEnabled = true
        }
        sundayButton.setOnClickListener() {
            currentDay = "SUNDAY"
            enableAllDays()
            sundayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        mondayButton.setOnClickListener() {
            currentDay = "MONDAY"
            enableAllDays()
            mondayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        tuesdayButton.setOnClickListener() {
            currentDay = "TUESDAY"
            enableAllDays()
            tuesdayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        wednesdayButton.setOnClickListener() {
            currentDay = "WEDNESDAY"
            enableAllDays()
            wednesdayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        thursdayButton.setOnClickListener() {
            currentDay = "THURSDAY"
            enableAllDays()
            thursdayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        fridayButton.setOnClickListener() {
            currentDay = "FRIDAY"
            enableAllDays()
            fridayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))
        }

        saturdayButton.setOnClickListener() {
            currentDay = "SATURDAY"
            enableAllDays()
            saturdayButton.isEnabled = false
            Log.d("DAYTAG", currentDay)
            adapter.setItems(schedule.getSessionsFromDay(currentDay))

        }
// --------------------------------------------------------------

        val startTimeSetListener: OnTimeSetListener =
            OnTimeSetListener { timePicker, hour, minute ->
                val f24: SimpleDateFormat = SimpleDateFormat("HH:mm")
                val date: Date = f24.parse("$hour:$minute")
                Log.d("TIMETAG", "onDateSet: dd/mm/yyy: ${f24.format(date)}")
                startTime = f24.format(date)
                startTimeButton.text = startTime
            }

        startTimeButton.setOnClickListener(View.OnClickListener {
            val cal: Calendar = Calendar.getInstance()
            val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cal.get(Calendar.MINUTE)
            val dialog = TimePickerDialog(
                this@ScheduleActivity,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, startTimeSetListener,
                8, 0, true
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })

        val endTimeSetListener: OnTimeSetListener = OnTimeSetListener { timePicker, hour, minute ->
            val f24: SimpleDateFormat = SimpleDateFormat("HH:mm")
            val date: Date = f24.parse("$hour:$minute")
            Log.d("TIMETAG", "onDateSet: dd/mm/yyy: ${f24.format(date)}")
            endTime = f24.format(date)
            endTimeButton.text = endTime
        }

        endTimeButton.setOnClickListener(View.OnClickListener {
            val cal: Calendar = Calendar.getInstance()
            val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
            val minutes: Int = cal.get(Calendar.MINUTE)
            val dialog = TimePickerDialog(
                this@ScheduleActivity,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, endTimeSetListener,
                8, 45, true
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        })

// --------------------------------------------------------------

        adapter.setItems(schedule.getSessionsFromDay(currentDay))

        createSessionButton.setOnClickListener {

            val description: String = editSessionView.text.toString()
            var newSession: Session = Session(
                description,
                LocalTime.parse(startTime),
                LocalTime.parse(endTime)
            )

            Log.d("the_session", newSession.description + " " + newSession.startTime.toString())

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

            mainSchedule.setSchedule(schedule)

            finish()
        }


    }


}