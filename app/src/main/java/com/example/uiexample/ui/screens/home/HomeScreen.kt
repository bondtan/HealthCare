package com.example.uiexample.ui.screens.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.uiexample.R
import com.example.uiexample.common.dateFormatSingleLine
import com.example.uiexample.common.timeFormat
import com.example.uiexample.ui.components.*
import com.example.uiexample.ui.screens.home.models.HomeViewState
import com.example.uiexample.ui.screens.home.views.CurrentMeasureCard
import com.example.uiexample.ui.screens.home.views.HealthCardItem
import com.example.uiexample.ui.screens.home.views.StickyHeaderHealthCardItem
import com.example.uiexample.ui.theme.ExampleTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navController: NavController
) {
    val viewState: HomeViewState by homeViewModel.viewState.collectAsStateWithLifecycle()

    var indexState = 0
    val state = rememberSaveable { mutableStateOf(indexState) }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = state.value)

    val dateDialogState = rememberMaterialDialogState()
    val timeDialogState = rememberMaterialDialogState()

    val formattedTime by remember {
        derivedStateOf { timeFormat(viewState.currentTime) }
    }

    val formattedDate by remember {
        derivedStateOf { dateFormatSingleLine(viewState.currentDate) }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ExampleTheme.colors.primaryBackground,
                        ExampleTheme.colors.secondaryBackground
                    )
                )
            )
    )
    {
        item {
            CurrentMeasureCard(
                viewState = viewState,
                homeViewModel = homeViewModel,
                dateDialogState = dateDialogState,
                timeDialogState = timeDialogState,
                formattedTime = formattedTime,
                formattedDate = formattedDate
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))
        }
        item {
            ExHeaderIconText(
                modifier = Modifier.padding(
                    start = dimensionResource(id = R.dimen.padding_large),
                    end = dimensionResource(id = R.dimen.padding_large)
                ),
                imageVector = Icons.Default.ListAlt,
                contentDescription = stringResource(id = R.string.list_measure),
                title = stringResource(id = R.string.list_measure)
            )
        }
        stickyHeader { StickyHeaderHealthCardItem() }
        val healthList = viewState.healthList
        when (healthList.isEmpty()) {
            true -> item {
                Text(
                    text = stringResource(id = R.string.add_value),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = dimensionResource(id = R.dimen.padding_large) * 3,
                            bottom = dimensionResource(id = R.dimen.padding_large) * 3
                        ),
                    textAlign = TextAlign.Center,
                    style = ExampleTheme.typography.subtitle,
                    color = ExampleTheme.colors.primaryText
                )
            }
            false -> itemsIndexed(items = healthList) { index, item ->
                indexState = index
                HealthCardItem(
                    modifier = Modifier.animateItemPlacement(
                        animationSpec = tween(durationMillis = 2000)
                    ),
                    model = item,
                    viewModel = homeViewModel
                )
            }
        }
    }
}