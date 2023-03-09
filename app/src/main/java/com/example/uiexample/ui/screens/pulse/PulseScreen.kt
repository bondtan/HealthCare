package com.example.uiexample.ui.screens.pulse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.uiexample.R
import com.example.uiexample.ui.components.ExHeaderDivider
import com.example.uiexample.ui.components.ExHeaderIconText
import com.example.uiexample.ui.components.ExHeaderText
import com.example.uiexample.ui.screens.pulse.models.PulseViewState
import com.example.uiexample.ui.screens.pulse.views.PulseGraph
import com.example.uiexample.ui.theme.ExampleTheme

@Composable
fun PulseScreen(
    pulseViewModel: PulseViewModel
) {
    val viewState: PulseViewState by pulseViewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ExampleTheme.colors.primaryBackground)
    ) {
        ExHeaderIconText(
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.padding_large),
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large),
                    bottom = dimensionResource(id = R.dimen.padding_small),
                ),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_show_chart),
            contentDescription = stringResource(id = R.string.pulse),
            title = stringResource(id = R.string.pulse)
        )
        ExHeaderDivider()
        when (viewState.pulseList.isEmpty()) {
            true -> {
                ExHeaderText(
                    text = stringResource(R.string.add_value),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(id = R.dimen.padding_large) * 5)
                )
            }
            false -> {
                PulseGraph(
                    viewState = viewState,
                    viewModel = pulseViewModel
                )
            }
        }
    }
}
