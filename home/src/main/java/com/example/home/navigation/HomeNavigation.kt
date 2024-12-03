package com.example.home.navigation

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.ui.HomeScreenViewModel
import com.example.home.ui.screens.HomeScreen

const val HOME_ROUTE = "Home"

fun NavGraphBuilder.homeScreen(
    navigateToDetailsScreen:()->Unit
) {
    composable(HOME_ROUTE) {
        val viewModel: HomeScreenViewModel = hiltViewModel()
        Text(text = "Hello", modifier = Modifier.clickable { navigateToDetailsScreen() })
    }
}