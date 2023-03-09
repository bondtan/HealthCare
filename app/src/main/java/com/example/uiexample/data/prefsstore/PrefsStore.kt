package com.example.uiexample.data.prefsstore

import com.example.uiexample.domain.models.ExNotificationSettingsBundle
import com.example.uiexample.domain.models.ExSettingsBundle
import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize
import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    fun getApplicationSettings(): Flow<ExSettingsBundle>

    fun getNotificationSettings(): Flow<ExNotificationSettingsBundle>

    suspend fun toggleDarkMode(value: Boolean)

    suspend fun toggleColorStyle(value: ExampleColorStyle)

    suspend fun toggleSizeTextStyle(value: ExampleSize)

    suspend fun toggleShapeStyle(value: ExampleShapeStyle)

    suspend fun toggleTimeShowNotification(value: String)
}