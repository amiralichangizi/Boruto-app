@file:OptIn(ExperimentalMaterialApi::class)

package com.tojare.borutoapp.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.tojare.borutoapp.presentation.screens.details.DetailsScreen
import com.tojare.borutoapp.presentation.screens.home.Home
import com.tojare.borutoapp.presentation.screens.search.SearchScreen
import com.tojare.borutoapp.presentation.screens.splash.SplashScreen
import com.tojare.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.tojare.borutoapp.util.Constant.DETAILS_ARGUMENT_KEY

@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController)
        }
        composable(route = Screen.Home.route) {
            Home(navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailsScreen(navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController)
        }

    }
}