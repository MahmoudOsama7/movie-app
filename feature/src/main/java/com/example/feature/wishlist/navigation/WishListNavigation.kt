package com.example.feature.wishlist.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.feature.wishlist.ui.screens.WatchListScreen
import com.example.feature.wishlist.ui.screens.WatchListViewModel

const val WATCH_LIST_ROUTE = "WatchList"

fun NavGraphBuilder.watchList(
    navigateToHomeScreen:()->Unit,
    navigateToDetailsScreen:(Int)->Unit
) {
    composable(WATCH_LIST_ROUTE) {
        val viewModel: WatchListViewModel = hiltViewModel()
        WatchListScreen(
            viewModel=viewModel,
            onStartAddingClicked = navigateToHomeScreen
        )
    }
}