package com.tojare.borutoapp.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tojare.borutoapp.R
import com.tojare.borutoapp.presentation.components.InfoBox
import com.tojare.borutoapp.ui.theme.welcomeScreenTitleColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel= hiltViewModel()
){
    val selectedHero = detailsViewModel.selectedHero

}