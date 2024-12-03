package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun TabsNavGraph(
    navigateToDetailsScreen:()->Unit
) {
    val tabsNavHostController = rememberNavController()
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            Column {
                BottomBar(navHostController = tabsNavHostController)
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = tabsNavHostController,
            startDestination = HOME_ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(HOME_ROUTE) {
                Text("Home")
            }
            composable(WISH_LIST_ROUTE) {
                Text("WishList")
            }
        }
    }
}