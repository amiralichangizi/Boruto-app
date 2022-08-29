package com.tojare.borutoapp.presentation.screens.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tojare.borutoapp.util.Constant.BASE_URL
import com.tojare.borutoapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.tojare.borutoapp.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.log

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette
    if (colorPalette.isNotEmpty()) {
        DetailsContent(
            navController = navController,
            hero = selectedHero,
            colors = colorPalette
        )
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPallet -> {
                    val bitmap = convertImageUrlToBitmap(
                        context = context,
                        imageUrl = "$BASE_URL${selectedHero?.image}"
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }

        }
    }


}
