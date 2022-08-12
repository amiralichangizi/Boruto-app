package com.tojare.borutoapp.presentation.screens.home


import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home() {
    Scaffold(
        topBar = {
            HomeTopBar {

            }
        }
    ) {
    }
}