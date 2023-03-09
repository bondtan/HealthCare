package com.example.uiexample.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uiexample.common.DEFAULT_TIME_SHOW_NOTIFICATION
import com.example.uiexample.common.EventHandler
import com.example.uiexample.data.prefsstore.PrefsStore
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.ui.screens.settings.models.SettingsEvent
import com.example.uiexample.ui.screens.settings.models.SettingsViewState
import com.example.uiexample.ui.theme.ExampleColorStyle
import com.example.uiexample.ui.theme.ExampleMode
import com.example.uiexample.ui.theme.ExampleShapeStyle
import com.example.uiexample.ui.theme.ExampleSize
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val prefsStore: PrefsStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) :
    ViewModel(), EventHandler<SettingsEvent> {

    private var _viewState = MutableStateFlow(
        SettingsViewState(
            ExampleMode.LIGHT,
            false, ExampleColorStyle.YELLOW, ExampleSize.SMALL, ExampleShapeStyle.ROUNDED,
            DEFAULT_TIME_SHOW_NOTIFICATION
        )
    )
    val viewState: StateFlow<SettingsViewState> = _viewState

    init {
        getAppSettings()
        getNotificationSettings()
    }

    override fun obtainEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SelectedMode -> setMode(event.value)
            is SettingsEvent.SelectedColor -> setColor(event.value)
            is SettingsEvent.SelectedSizeText -> setSizeText(event.value)
            is SettingsEvent.SelectedShape -> setShape(event.value)
            is SettingsEvent.SelectedTimeShowNotification -> setTimeShowNotification(event.value)
        }
    }

    fun getAppSettings() = viewModelScope.launch {
        withContext(ioDispatcher) {
            prefsStore.getApplicationSettings().collect {
                when (it.isDarkMode) {
                    true -> _viewState.update { currentViewState ->
                        currentViewState.copy(
                            selectedMode = ExampleMode.DARK,
                            isSelectedDarkMode = true,
                            selectedColor = it.color,
                            selectedSizeText = it.sizeText,
                            selectedShape = it.shape
                        )
                    }
                    false -> _viewState.update { currentViewState ->
                        currentViewState.copy(
                            selectedMode = ExampleMode.LIGHT,
                            isSelectedDarkMode = false,
                            selectedColor = it.color,
                            selectedSizeText = it.sizeText,
                            selectedShape = it.shape
                        )
                    }
                }
            }
        }
    }

    fun getNotificationSettings() = viewModelScope.launch {
        withContext(ioDispatcher) {
            prefsStore.getNotificationSettings().collect {
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        selectedTimeShowNotification = it.time
                    )
                }
            }
        }
    }

    private fun setMode(value: Boolean) = viewModelScope.launch {
        when (value) {
            true -> {
                prefsStore.toggleDarkMode(false)
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        selectedMode = ExampleMode.LIGHT,
                        isSelectedDarkMode = false
                    )
                }
            }
            false -> {
                prefsStore.toggleDarkMode(true)
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        selectedMode = ExampleMode.DARK,
                        isSelectedDarkMode = true
                    )
                }
            }
        }
    }

    private fun setColor(value: ExampleColorStyle) = viewModelScope.launch {
        prefsStore.toggleColorStyle(value)
        _viewState.update { currentViewState ->
            currentViewState.copy(selectedColor = value)
        }
    }

    private fun setSizeText(value: ExampleSize) = viewModelScope.launch {
        prefsStore.toggleSizeTextStyle(value)
        _viewState.update { currentViewState ->
            currentViewState.copy(selectedSizeText = value)
        }
    }

    private fun setShape(value: ExampleShapeStyle) = viewModelScope.launch {
        prefsStore.toggleShapeStyle(value)
        _viewState.update { currentViewState ->
            currentViewState.copy(selectedShape = value)
        }
    }

    private fun setTimeShowNotification(value: String) = viewModelScope.launch {
        prefsStore.toggleTimeShowNotification(value)
        _viewState.update { currentViewState ->
            currentViewState.copy(selectedTimeShowNotification = value)
        }
    }
}