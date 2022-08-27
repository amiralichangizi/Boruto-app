package com.tojare.borutoapp.presentation.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlin.math.log

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel= hiltViewModel()
){
    val selectedHero by detailsViewModel.selectedHero.collectAsState()

    DetailsContent(navController = navController, hero = selectedHero)

}