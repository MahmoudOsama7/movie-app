package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.navigation.tabsNavigation.TABS
import com.example.navigation.tabsNavigation.tabsFlow

const val MAIN_ROUTE = "main_route"

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE
    ) {
        navigation(
            startDestination = TABS,
            route = MAIN_ROUTE
        ) {
            tabsFlow(
                navigateToDetailsScreen = navController::navigateToDetailsScreen
            )
            detailsNavigation()  // Define your detailsNavigation logic here if needed
        }
    }
}

