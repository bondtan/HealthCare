package com.example.uiexample.ui.screens.pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.uiexample.R
import com.example.uiexample.ui.components.ExHeaderDivider
import com.example.uiexample.ui.components.ExHeaderIconText
import com.example.uiexample.ui.components.ExHeaderText
import com.example.uiexample.ui.screens.pressure.models.PressureViewState
import com.example.uiexample.ui.screens.pressure.views.PressureAnimationIcon
import com.example.uiexample.ui.screens.pressure.views.PressureItem
import com.example.uiexample.ui.screens.pressure.views.PressureLazyRow
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun PressureScreen(
    pressureViewModel: PressureViewModel
) {
    val viewState: PressureViewState by pressureViewModel.viewState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ExampleTheme.colors.primaryBackground)
    ) {
        item {
            ExHeaderIconText(
                modifier = Modifier
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_large),
                        start = dimensionResource(id = R.dimen.padding_large),
                        end = dimensionResource(id = R.dimen.padding_large),
                        bottom = dimensionResource(id = R.dimen.padding_small),
                    ),
                imageVector = Icons.Default.HeartBroken,
                contentDescription = stringResource(id = R.string.blood_pressure),
                title = stringResource(id = R.string.blood_pressure)
            )
            ExHeaderDivider()
        }
        item {
            when (viewState.pressureList.isEmpty()) {
                true -> ExHeaderText(text = stringResource(R.string.add_value),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(id = R.dimen.padding_large) * 5))
                false -> {
                    PressureLazyRow(
                        viewState = viewState,
                        pressureViewModel = pressureViewModel
                    )
                    ExHeaderDivider(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.padding_large)
                        )
                    )
                    when (viewState.selectIdItem == -1) {
                        true -> {
                            ExHeaderText(
                                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_large)),
                                text = stringResource(R.string.choose_day)
                            )
                            PressureAnimationIcon()
                        }
                        else -> PressureItem(viewState = viewState)
                    }
                }
            }
        }
    }
}
