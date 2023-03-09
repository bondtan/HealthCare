package com.example.uiexample.notification.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.uiexample.R
import com.example.uiexample.ui.MainActivity

private const val NOTIFICATION_ID = 0

fun NotificationManager.sendReminderNotification(
    channelId: String, applicationContext: Context
){
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notification = NotificationCompat.Builder(
        applicationContext, channelId)
        .setSmallIcon(R.drawable.ic_heart)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(applicationContext.getString(R.string.notification_text))
        .setColor(applicationContext.getColor(R.color.red_500))
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        .setContentIntent(contentPendingIntent)

    notify(NOTIFICATION_ID, notification.build())
}

fun NotificationManager.cancelNotifications(){
    cancelAll()
}
