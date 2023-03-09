package com.example.uiexample.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import java.util.*
import javax.inject.Inject

class ReminderManagerImpl @Inject constructor() : ReminderManager {

    override fun startReminder(
        context: Context,
        reminderTime: String,
        reminderId: Int,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, minute) = reminderTime.split(":").map { it.toInt() }
        val intent = Intent(context, AlarmReceiver::class.java)
            .let { intent ->
                intent.putExtra(NOTIFICATION_TIME, reminderTime)
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }
        val calendar = Calendar.getInstance(Locale.getDefault()).apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minute)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, intent
        )
    }

    override fun stopReminder(
        context: Context,
        reminderId: Int
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context, reminderId, intent, PendingIntent.FLAG_IMMUTABLE
            )
        }
        alarmManager.cancel(intent)
    }

    companion object {
        const val REMINDER_NOTIFICATION_REQUEST_CODE = 10000
        const val NOTIFICATION_TIME = "notification_time"
    }
}