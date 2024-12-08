package com.example.navigation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

const val HOME_ROUTE = "Home"
const val WISH_LIST_ROUTE = "WatchList"

sealed class BottomBarTabs(
    val route: String,
    val name:String,
    val icon: ImageVector
) {
    data object Home : BottomBarTabs(HOME_ROUTE,"Home", Icons.Filled.Home  )
    data object WatchList : BottomBarTabs(WISH_LIST_ROUTE, "WatchList",Icons.Filled.Favorite)
}