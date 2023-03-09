package com.example.uiexample.ui.screens.pressure.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.uiexample.R
import com.example.uiexample.ui.screens.pressure.PressureViewModel
import com.example.uiexample.ui.screens.pressure.models.PressureEvent
import com.example.uiexample.ui.screens.pressure.models.PressureViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PressureLazyRow(
    viewState: PressureViewState,
    pressureViewModel: PressureViewModel
) {
    Box {
        PressureGrid(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_large))
        )
        LazyRow(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_large))
        ) {
            stickyHeader { PressureHeader() }
            val pressureList = viewState.pressureList
            items(items = pressureList) { item ->
                PressureGraphCardItem(model = item,
                onCardItemClick = { pressureViewModel.obtainEvent(
                    PressureEvent.SelectItem(item.id ?: 0))
                })
            }
        }
    }
}