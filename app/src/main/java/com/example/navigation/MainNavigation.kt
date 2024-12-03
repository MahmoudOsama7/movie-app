package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.home.navigation.tabsFlow

const val MAIN_ROUTE = "main_route"
@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE
    ) {
        navigation(
            startDestination = com.example.home.navigation.TABS,
            route = MAIN_ROUTE
        ) {
            tabsFlow(navigateToDetailsScreen = navController::navigateToDetailsScreen)
            detailsNavigation()
        }
    }
}