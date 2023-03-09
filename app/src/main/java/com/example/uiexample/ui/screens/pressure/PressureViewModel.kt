package com.example.uiexample.ui.screens.pressure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uiexample.common.EventHandler
import com.example.uiexample.di.IoDispatcher
import com.example.uiexample.domain.models.fromDomainToPressureUi
import com.example.uiexample.domain.usecases.GetHealthItemUseCase
import com.example.uiexample.domain.usecases.GetHealthListUseCase
import com.example.uiexample.ui.screens.pressure.models.PressureEvent
import com.example.uiexample.ui.screens.pressure.models.PressureViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PressureViewModel @Inject constructor(
    private val getHealthListUseCase: GetHealthListUseCase,
    private val getHealthItemUseCase: GetHealthItemUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), EventHandler<PressureEvent> {

    private val _viewState = MutableStateFlow(PressureViewState())
    val viewState: StateFlow<PressureViewState> = _viewState

    init {
        fetchPressureList()
    }

    override fun obtainEvent(event: PressureEvent) {
        when (event) {
            is PressureEvent.SelectItem -> fetchItem(event.value)
        }
    }


    private fun fetchPressureList() = viewModelScope.launch {
        withContext(ioDispatcher) {
            getHealthListUseCase.invoke().collect { list ->
                _viewState.update { currentViewState ->
                    currentViewState.copy(
                        pressureList = list.map { it.fromDomainToPressureUi() }
                    )
                }
            }
        }
    }

    private fun fetchItem(idItem: Int) =
        viewModelScope.launch {
            try {
                withContext(ioDispatcher) {
                    getHealthItemUseCase.invoke(idItem).collect {
                        _viewState.update { currentViewState ->
                            currentViewState.copy(
                                selectIdItem = idItem,
                                selectPressureItem = it.fromDomainToPressureUi()
                            )
                        }
                    }
                }
            } catch (_: NullPointerException) {
                _viewState.update { currentViewState ->
                    currentViewState.copy(selectIdItem = -1)
                }
            }

        }
}