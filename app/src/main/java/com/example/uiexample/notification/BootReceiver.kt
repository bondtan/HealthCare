package com.example.uiexample.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.uiexample.data.prefsstore.PrefsStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootReceiver : BroadcastReceiver() {

    @Inject
    lateinit var reminderManager: ReminderManager

    @Inject
    lateinit var prefsStore: PrefsStore

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "android.intent.action.BOOT_COMPLETED") {

            CoroutineScope(Dispatchers.Unconfined).launch {

                val settings = prefsStore.getNotificationSettings().first()

                reminderManager.startReminder(
                    context.applicationContext, settings.time,
                    ReminderManagerImpl.REMINDER_NOTIFICATION_REQUEST_CODE
                )
                Log.d("Boot receiver", "Time: ${settings.time}")
            }
        }
    }
}