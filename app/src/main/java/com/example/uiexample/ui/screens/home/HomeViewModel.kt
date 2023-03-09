package com.example.uiexample.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uiexample.common.*
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.domain.models.fromDomainToUi
import com.example.uiexample.domain.usecases.DeleteHealthItemUseCase
import com.example.uiexample.domain.usecases.GetHealthListUseCase
import com.example.uiexample.domain.usecases.InsetHealthItemUseCase
import com.example.uiexample.ui.screens.home.models.HealthUI
import com.example.uiexample.ui.screens.home.models.HomeEvent
import com.example.uiexample.ui.screens.home.models.HomeViewState
import com.example.uiexample.ui.screens.home.models.fromUiToDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val deleteHealthItemUseCase: DeleteHealthItemUseCase,
    private val insetHealthItemUseCase: InsetHealthItemUseCase,
    private val getHealthListUseCase: GetHealthListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), EventHandler<HomeEvent> {

    private val _viewState = MutableStateFlow(
        HomeViewState(
            isSystolicPressureValid = true,
            isDiastolicPressureValid = true,
            isPulseValid = true
        )
    )
    val viewState: StateFlow<HomeViewState> = _viewState

    init {
        fetchHealthList()
    }

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.TimeChanged -> timeChanged(event.value)
            is HomeEvent.DataChanged -> dateChanged(event.value)
            is HomeEvent.SystolicPressureChanged -> systolicPressureChanged(event.value)
            is HomeEvent.DiastolicPressureChanged -> diastolicPressureChanged(event.value)
            is HomeEvent.PulseChanged -> pulseChanged(event.value)
            HomeEvent.SaveClicked -> saveClicked()
            is HomeEvent.DeleteSelectHealthCardItem -> deleteHealthItem(event.value)
        }
    }

    private fun timeChanged(value: LocalTime) {
        _viewState.update { currentViewState ->
            currentViewState.copy(currentTime = value)
        }
    }

    private fun dateChanged(value: LocalDate) {
        _viewState.update { currentViewState ->
            currentViewState.copy(currentDate = value)
        }
    }

    private fun systolicPressureChanged(value: String) {
        _viewState.update { currentViewState ->
            currentViewState.copy(currentSystolicPressure = value.toInt())
        }
        isSystolicPressureValid()
        shouldEnableSaveButton()
    }

    private fun diastolicPressureChanged(value: String) {
        _viewState.update { currentViewState ->
            currentViewState.copy(currentDiastolicPressure = value.toInt())
        }
        isDiastolicPressureValid()
        shouldEnableSaveButton()
    }

    private fun isSystolicPressureValid() {
        when (_viewState.value.currentSystolicPressure in
                MIN_SYSTOLIC_PRESSURE..MAX_SYSTOLIC_PRESSURE) {
            true -> _viewState.update { currentViewState ->
                currentViewState.copy(isSystolicPressureValid = true)
            }
            false -> _viewState.update { currentViewState ->
                currentViewState.copy(isSystolicPressureValid = false)
            }
        }
    }

    private fun isDiastolicPressureValid() {
        when (_viewState.value.currentDiastolicPressure in
                MIN_DIASTOLIC_PRESSURE..MAX_DIASTOLIC_PRESSURE &&
                _viewState.value.currentSystolicPressure >= _viewState.value.currentDiastolicPressure
        ) {
            true -> _viewState.update { currentViewState ->
                currentViewState.copy(isDiastolicPressureValid = true)
            }
            false -> _viewState.update { currentViewState ->
                currentViewState.copy(isDiastolicPressureValid = false)
            }
        }
    }

    private fun pulseChanged(value: String) {
        _viewState.update { currentViewState ->
            currentViewState.copy(currentPulse = value.toInt())
        }
        isPulseValid()
        shouldEnableSaveButton()
    }

    private fun isPulseValid() {
        when (_viewState.value.currentPulse in MIN_PULSE..MAX_PULSE) {
            true -> _viewState.update { currentViewState ->
                currentViewState.copy(isPulseValid = true)
            }
            false -> _viewState.update { currentViewState ->
                currentViewState.copy(isPulseValid = false)
            }
        }
    }

    private fun saveClicked() {
        val tempHealthUI = HealthUI(
            time = _viewState.value.currentTime,
            date = _viewState.value.currentDate,
            systolicPressure = _viewState.value.currentSystolicPressure,
            diastolicPressure = _viewState.value.currentDiastolicPressure,
            pulse = _viewState.value.currentPulse
        )
        saveHealthItem(tempHealthUI)
        cleanHomeViewState()
    }

    private fun shouldEnableSaveButton() {
        _viewState.update { currentViewState ->
            currentViewState.copy(
                isEnableSaveButton = _viewState.value.isSystolicPressureValid &&
                        _viewState.value.isDiastolicPressureValid &&
                        _viewState.value.isPulseValid &&
                        _viewState.value.currentSystolicPressure != 0 &&
                        _viewState.value.currentDiastolicPressure != 0 &&
                        _viewState.value.currentPulse != 0 &&
                        _viewState.value.currentSystolicPressure >= _viewState.value.currentDiastolicPressure
            )
        }
    }

    private fun cleanHomeViewState() {
        _viewState.update { currentViewState ->
            currentViewState.copy(
                currentTime = LocalTime.now(),
                currentDate = LocalDate.now(),
                currentSystolicPressure = 0,
                isSystolicPressureValid = true,
                currentDiastolicPressure = 0,
                isDiastolicPressureValid = true,
                currentPulse = 0,
                isPulseValid = true,
                isEnableSaveButton = false,
            )
        }
    }

    private fun saveHealthItem(healthUI: HealthUI) =
        viewModelScope.launch {
            withContext(ioDispatcher) {
                insetHealthItemUseCase.invoke(healthUI.fromUiToDomain())
            }
        }

    private fun deleteHealthItem(healthUI: HealthUI) =
        viewModelScope.launch {
            withContext(ioDispatcher) {
                deleteHealthItemUseCase.invoke(healthUI.fromUiToDomain())
            }
        }

    private fun fetchHealthList() = viewModelScope.launch {
        withContext(ioDispatcher) {
            getHealthListUseCase.invoke().collect { list ->
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        healthList = list.map { it.fromDomainToUi() }
                    )
                }
            }
        }
    }
}