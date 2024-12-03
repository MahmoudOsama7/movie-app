package com.example.navigation.bottomBar

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(
    navHostController: NavHostController,
) {
    val items = listOf(
        BottomBarTabs.Home,
        BottomBarTabs.WishList
    )

    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val selectedTab = remember { mutableStateOf<BottomBarTabs>(BottomBarTabs.Home) }


    LaunchedEffect(currentDestination) {
        selectedTab.value = items.firstOrNull { it.route == currentDestination?.route } ?: BottomBarTabs.Home
    }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedTab.value == screen,
                label = {
                    Text(
                        text=screen.name,
                    )
                },
                icon = {},
                onClick = {
                    navHostController.navigate(screen.route) {
                        popUpTo(screen.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}

