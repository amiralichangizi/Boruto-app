package com.tojare.borutoapp.presentation.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tojare.borutoapp.R
import com.tojare.borutoapp.ui.theme.topBarBackgroundColor
import com.tojare.borutoapp.ui.theme.topBarContentColor

@Composable
fun HomeTopBar(onSearchedClick : ()-> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Explore",
                color = MaterialTheme.colors.topBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
        actions = {
            IconButton(
                onClick = onSearchedClick
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = stringResource(R.string.search_icon))
            }
        }
    )
}

@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar {

    }
}
