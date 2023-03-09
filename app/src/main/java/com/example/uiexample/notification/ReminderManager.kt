package com.example.uiexample.notification

import android.content.Context

interface ReminderManager {

    fun startReminder(context: Context, reminderTime: String, reminderId: Int)

    fun stopReminder(context: Context, reminderId: Int)
}