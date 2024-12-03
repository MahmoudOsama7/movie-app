package com.example.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val DETAILS="details"

fun NavHostController.navigateToDetailsScreen(){
    navigate(DETAILS)
}


fun NavGraphBuilder.detailsNavigation() {
    composable(
        route = DETAILS,
    ) {
        Text("Details")
    }
}