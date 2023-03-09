package com.example.uiexample.ui.screens.pressure.views

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.uiexample.R
import com.example.uiexample.ui.theme.ExampleTheme
import kotlinx.coroutines.delay

@Composable
fun PressureAnimationIcon(
    modifier: Modifier = Modifier
) {
    val waves = listOf(
        remember { Animatable(0f) },
        remember { Animatable(0f) },
        remember { Animatable(0f) },
    )
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(3000, easing = FastOutLinearInEasing),
        repeatMode = RepeatMode.Restart
    )
    waves.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 1000L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = animationSpec
            )
        }
    }
    val wavesDys = waves.map { it.value }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = ExampleTheme.colors.primaryBackground),
        contentAlignment = Alignment.Center
    ) {
       wavesDys.forEach { dy ->
            Box(modifier = Modifier
                .size(70.dp)
                .align(Alignment.Center)
                .graphicsLayer {
                    scaleX = dy * 2 + 1 //dy * 4 + 1
                    scaleY = dy * 2 + 1 //dy * 4 + 1
                    alpha = 1 - dy
                }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = ExampleTheme.colors.secondaryBackground,
                            shape = CircleShape
                        )
                )
            }
        }
        Icon(
            modifier = Modifier.size(80.dp),
        tint = ExampleTheme.colors.primaryText.copy(alpha = 0.8f),
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_touch_app),
        contentDescription = stringResource(id = R.string.choose_day))
    }
}