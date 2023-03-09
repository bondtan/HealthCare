package com.example.uiexample.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.uiexample.R
import com.example.uiexample.common.NOTIFICATION_CHANNEL_ID
import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.notification.ReminderManager
import com.example.uiexample.notification.ReminderManagerImpl
import com.example.uiexample.ui.screens.mainscreen.MainScreenView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var prefsStore: PrefsStore

    @Inject
    @IoDispatcher
    lateinit var ioDispatchers: CoroutineDispatcher

    @Inject
    lateinit var reminderManager: ReminderManager

    private val mainViewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.notificationSettings.collect {

                    reminderManager.startReminder(
                        applicationContext,
                        it.time,
                        ReminderManagerImpl.REMINDER_NOTIFICATION_REQUEST_CODE)
                }
            }
        }
        setContent {
            MainScreenView(
                mainViewModel = mainViewModel,
                prefsStore = prefsStore,
                ioDispatcher = ioDispatchers
            )
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                getString(R.string.name_notification_cannel),
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    description = getString(R.string.description_notification_channel)
                }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}



