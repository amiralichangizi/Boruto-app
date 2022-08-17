package com.tojare.borutoapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tojare.borutoapp.R
import com.tojare.borutoapp.domain.model.Hero
import com.tojare.borutoapp.ui.theme.DarkGray
import com.tojare.borutoapp.ui.theme.LightGray
import com.tojare.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null, heroes: LazyPagingItems<Hero>? = null) {
    var message by remember {
        mutableStateOf("Find Your Favorite Hero")
    }
    var icon by remember {
        mutableStateOf(R.drawable.ic_search_document)
    }

    if (error != null) {
        message = parseErrorMessage(error)
        icon = R.drawable.ic_network_error
    }

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val alpha by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    LaunchedEffect(
        key1 = true,
    ) {
        startAnimation = true
    }

    EmptyContent(alpha = alpha, icon = icon, message = message, heroes = heroes, error = error)

}

@Composable
fun EmptyContent(
    alpha: Float,
    icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>?,
    error: LoadState.Error? = null
) {
    var isRefreshing by remember {
        mutableStateOf(false)
    }

    SwipeRefresh(
        swipeEnabled = error != null,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false

        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(120.dp)
                    .alpha(alpha),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) LightGray else DarkGray
            )
            Text(
                modifier = Modifier
                    .padding(top = SMALL_PADDING)
                    .alpha(alpha),
                text = message,
                color = if (isSystemInDarkTheme()) LightGray else DarkGray,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.typography.subtitle1.fontSize

            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyScreenPreview() {
    EmptyContent(
        alpha = ContentAlpha.disabled,
        icon = R.drawable.ic_network_error,
        message = "Internet Unavailable",
        heroes = null,
        error = null
    )
}

fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable"
        }
        is ConnectException -> {
            "Internet Unavailable"
        }
        else -> {
            "Unknown Error"
        }
    }
}