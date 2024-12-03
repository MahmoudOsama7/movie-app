package com.example.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.navigation.navGraph.TabsNavGraph

const val TABS="tabs"

fun NavGraphBuilder.tabsFlow(
    navigateToDetailsScreen:()->Unit
) {
    composable(
        route = TABS,
    ) {
        TabsNavGraph(
            navigateToDetailsScreen=navigateToDetailsScreen
        )
    }
}