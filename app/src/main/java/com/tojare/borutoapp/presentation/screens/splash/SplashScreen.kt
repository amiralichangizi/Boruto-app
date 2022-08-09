package com.tojare.borutoapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.tojare.borutoapp.R
import com.tojare.borutoapp.ui.theme.Purple500
import com.tojare.borutoapp.ui.theme.Purple700

@Composable
fun SplashScreen(navController: NavHostController? = null) {
    val degree = remember { Animatable(0f) }
    LaunchedEffect(key1 = true) {
        degree.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
    }
    Splash(degree.value)
}

@Composable
fun Splash(degree: Float) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        Brush.verticalGradient(listOf(Color.Black, Color.Black))
    } else {
        Brush.verticalGradient(listOf(Purple700, Purple500))
    }
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(R.string.app_logo),
            modifier = Modifier.rotate(degrees = degree)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen()
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    SplashScreen()
}