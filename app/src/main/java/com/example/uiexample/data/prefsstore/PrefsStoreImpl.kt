package com.example.uiexample.data.prefsstore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.*
import com.example.uiexample.common.DEFAULT_TIME_SHOW_NOTIFICATION
import com.example.uiexample.domain.models.ExNotificationSettingsBundle
import com.example.uiexample.domain.models.ExSettingsBundle
import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefsStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PrefsStore {

    override fun getApplicationSettings(): Flow<ExSettingsBundle> =
        dataStore.data.catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }.map {
            ExSettingsBundle(
                isDarkMode = it[PreferencesKeys.DARK_MODE_KEY] ?: false,
                color = when (it[PreferencesKeys.COLOR_KEY]) {
                    1 -> ExampleColorStyle.YELLOW
                    2 -> ExampleColorStyle.BLUE
                    3 -> ExampleColorStyle.GRAY
                    4 -> ExampleColorStyle.PURPLE
                    else -> ExampleColorStyle.YELLOW
                },
                sizeText = when (it[PreferencesKeys.SIZE_TEXT_KEY]) {
                    1 -> ExampleSize.SMALL
                    2 -> ExampleSize.BIG
                    else -> ExampleSize.SMALL
                },
                shape = when (it[PreferencesKeys.SHAPE_KEY]) {
                    1 -> ExampleShapeStyle.ROUNDED
                    2 -> ExampleShapeStyle.SQUIRE
                    else -> ExampleShapeStyle.ROUNDED
                }
            )
        }

    override fun getNotificationSettings(): Flow<ExNotificationSettingsBundle> =
        dataStore.data.catch { exception ->
            when (exception) {
                is IOException -> emit(emptyPreferences())
                else -> throw exception
            }
        }.map {
            ExNotificationSettingsBundle(
                time = it[PreferencesKeys.NOTIFICATION_SHOW_TIME] ?: DEFAULT_TIME_SHOW_NOTIFICATION
            )
    }

    override suspend fun toggleDarkMode(value: Boolean) {
        dataStore.edit { it[PreferencesKeys.DARK_MODE_KEY] = value }
    }

    override suspend fun toggleColorStyle(value: ExampleColorStyle) {
        val temp = when (value) {
            ExampleColorStyle.YELLOW -> 1
            ExampleColorStyle.BLUE -> 2
            ExampleColorStyle.GRAY -> 3
            ExampleColorStyle.PURPLE -> 4
        }
        dataStore.edit { it[PreferencesKeys.COLOR_KEY] = temp }
    }

    override suspend fun toggleSizeTextStyle(value: ExampleSize) {
        val temp = when (value) {
            ExampleSize.SMALL -> 1
            ExampleSize.BIG -> 2
        }
        dataStore.edit { it[PreferencesKeys.SIZE_TEXT_KEY] = temp }
    }

    override suspend fun toggleShapeStyle(value: ExampleShapeStyle) {
        val temp = when (value) {
            ExampleShapeStyle.ROUNDED -> 1
            ExampleShapeStyle.SQUIRE -> 2
        }
        dataStore.edit { it[PreferencesKeys.SHAPE_KEY] = temp }
    }

    override suspend fun toggleTimeShowNotification(value: String) {
        dataStore.edit { it[PreferencesKeys.NOTIFICATION_SHOW_TIME] = value }
        Log.d("Pref", "time notification $value")

    }

    private object PreferencesKeys {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode_enabled")
        val COLOR_KEY = intPreferencesKey("color_number")
        val SIZE_TEXT_KEY = intPreferencesKey("size_number")
        val SHAPE_KEY = intPreferencesKey("shape_number")
        val NOTIFICATION_SHOW_TIME =   stringPreferencesKey("notification_time")
    }
}