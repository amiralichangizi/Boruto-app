package com.tojare.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tojare.borutoapp.presentation.common.ListContent
import com.tojare.borutoapp.presentation.screens.search.SearchTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                               searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                                  searchViewModel.searchHeroes(it)
                },
                onCloseClicked = {
                    navController.navigateUp()
                }
            )
        }

    ) {

        ListContent(heroes = heroes, navController = navController)

    }
}