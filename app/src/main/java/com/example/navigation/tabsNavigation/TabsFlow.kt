package com.example.navigation.tabsNavigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val TABS = "tabs"

fun NavGraphBuilder.tabsFlow(
    navigateToDetailsScreen: (Int) -> Unit
) {
    composable(
        route = TABS
    ) {
        TabsNavGraph(navigateToDetailsScreen=navigateToDetailsScreen)
    }
}
