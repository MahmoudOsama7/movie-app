package com.example.navigation.tabsNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.home.navigation.homeScreen
import com.example.navigation.bottomBar.BottomBar
import com.example.navigation.bottomBar.HOME_ROUTE


@Composable
fun TabsNavGraph(
    navigateToDetailsScreen: () -> Unit
) {
    val tabsNavController = rememberNavController()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Column {
                BottomBar(navHostController = tabsNavController)
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = tabsNavController,
            startDestination = HOME_ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            homeScreen(navigateToDetailsScreen)
        }
    }
}
