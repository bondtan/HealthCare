package com.example.uiexample.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uiexample.common.DEFAULT_TIME_SHOW_NOTIFICATION
import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.domain.models.ExNotificationSettingsBundle
import com.example.uiexample.domain.models.ExSettingsBundle
import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val prefsStore: PrefsStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _applicationSettings = MutableStateFlow(
        ExSettingsBundle(
            false, ExampleColorStyle.YELLOW, ExampleSize.SMALL, ExampleShapeStyle.ROUNDED
        )
    )
    val applicationsSettings: StateFlow<ExSettingsBundle> = _applicationSettings

    private var _notificationSettings = MutableStateFlow(
        ExNotificationSettingsBundle(DEFAULT_TIME_SHOW_NOTIFICATION)
    )
    val notificationSettings: StateFlow<ExNotificationSettingsBundle> = _notificationSettings

    init {
        getApplicationSettings()
        getNotificationSettings()
    }

    fun getApplicationSettings() = viewModelScope.launch {
        withContext(ioDispatcher) {
            prefsStore.getApplicationSettings().collect {
                _applicationSettings.value = it
            }
        }
    }

    private fun getNotificationSettings() = viewModelScope.launch {
        withContext(ioDispatcher) {
            prefsStore.getNotificationSettings().collect {
                _notificationSettings.value = it
            }
            Log.d("Main View model", "Notification time ${_notificationSettings.value}")
        }
    }
}