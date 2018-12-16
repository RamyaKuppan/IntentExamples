package com.intentexample

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*


class AlarmActivity : AppCompatActivity() {
    private var timePickerDialog: TimePickerDialog? = null
    private val RQS_1 = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        set_alarm.setOnClickListener({
            openTimePickerDialog(false)
        })
    }

    private fun openTimePickerDialog(is24r: Boolean) {
        val calendar = Calendar.getInstance()

        timePickerDialog = TimePickerDialog(this,
                onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), is24r)
        timePickerDialog!!.setTitle("Set Alarm Time")

        timePickerDialog!!.show()

    }

    private var onTimeSetListener: OnTimeSetListener = OnTimeSetListener { view, hourOfDay, minute ->
        val calNow = Calendar.getInstance()
        val calSet = calNow.clone() as Calendar

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calSet.set(Calendar.MINUTE, minute)
        calSet.set(Calendar.SECOND, 0)
        calSet.set(Calendar.MILLISECOND, 0)

        if (calSet <= calNow) {
            // Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1)
        }

        setAlarm(calSet)
    }

    private fun setAlarm(targetCal: Calendar) {

        message.text = ("\n\n***\n" + "Alarm is set "
                + targetCal.time + "\n" + "***\n")

        val intent = Intent(baseContext, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
                baseContext, RQS_1, intent, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.timeInMillis,
                pendingIntent)

    }
}
