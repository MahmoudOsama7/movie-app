package com.example.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.ui.screens.HomeScreenViewModel
import com.example.home.ui.screens.HomeScreen

const val HOME_ROUTE = "Home"

fun NavGraphBuilder.homeScreen(
    navigateToDetailsScreen:()->Unit
) {
    composable(HOME_ROUTE) {
        val viewModel: HomeScreenViewModel = hiltViewModel()
        HomeScreen(viewModel=viewModel)
    }
}