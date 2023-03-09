package com.example.uiexample.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.uiexample.R
import com.example.uiexample.common.DEFAULT_TIME_SHOW_NOTIFICATION
import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.notification.utils.sendReminderNotification
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject lateinit var reminderManager: ReminderManager
    @Inject lateinit var prefsStore: PrefsStore

   /* private var notificationSettings: MutableStateFlow<ExNotificationSettingsBundle> =
        MutableStateFlow(
            ExNotificationSettingsBundle(
                DEFAULT_INDEX_COUNT_NOTIFICATION, DEFAULT_TIME_SHOW_NOTIFICATION
        ))*/


         //private var notificationSettings = ExNotificationSettingsBundle(
        //     DEFAULT_INDEX_COUNT_NOTIFICATION, DEFAULT_TIME_SHOW_NOTIFICATION)

         /*init {
             getNotificationSettings()
             Log.d("AlarmBroadCast","${notificationSettings.value}")
         }
*/

    override fun onReceive(context: Context, intent: Intent) {

       val time = intent.getStringExtra(ReminderManagerImpl.NOTIFICATION_TIME) ?: DEFAULT_TIME_SHOW_NOTIFICATION
        Log.d("Alarm onReceive", "time: $time")

        val notificationManager = ContextCompat.getSystemService(
            context, NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            context.getString(R.string.health_notification), context
        )
/*
        reminderManager.startReminder(
            context.applicationContext, "08:00",
            ReminderManagerImpl.REMINDER_NOTIFICATION_REQUEST_CODE
        )*/

       reminderManager.startReminder(
            context.applicationContext, time,
            ReminderManagerImpl.REMINDER_NOTIFICATION_REQUEST_CODE)
        /*reminderManager.startReminder(
            context.applicationContext, notificationSettings.value.time,
            ReminderManagerImpl.REMINDER_NOTIFICATION_REQUEST_CODE,
            notificationSettings.value.indexArrayCount
        )*/
    }

    /*private fun getNotificationSettings() {
        CoroutineScope(Dispatchers.Main).launch {
            prefsStore.getNotificationSettings().first {
                notificationSettings.value = it
                true
            }
        }
    }*/
}
