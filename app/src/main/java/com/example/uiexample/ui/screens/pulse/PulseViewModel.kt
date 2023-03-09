package com.example.uiexample.ui.screens.pulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uiexample.common.EventHandler
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.domain.models.fromDomainToPulseUi
import com.example.uiexample.domain.usecases.GetHealthListUseCase
import com.example.uiexample.ui.screens.pulse.models.PulseEvent
import com.example.uiexample.ui.screens.pulse.models.PulseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PulseViewModel @Inject constructor(
    private val getHealthListUseCase: GetHealthListUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), EventHandler<PulseEvent> {

    private val _viewState = MutableStateFlow(PulseViewState())
    val viewState: StateFlow<PulseViewState> = _viewState

    init {
        fetchPulseList()
    }

    override fun obtainEvent(event: PulseEvent) {
        when (event) {
            is PulseEvent.SelectPulseItem -> fetchPulseItem(event.value)
        }
    }

    private fun fetchPulseList() = viewModelScope.launch {
        withContext(ioDispatcher) {
            getHealthListUseCase.invoke().collect { list ->
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        pulseList = list.map { it.fromDomainToPulseUi() }
                    )
                }
            }
        }
    }

    private fun fetchPulseItem (event: Int) {
        _viewState.update { currentViewState ->
            currentViewState.copy(selectPulseItem = currentViewState.pulseList[event],
            isSelectedItem = true)
        }
    }

}