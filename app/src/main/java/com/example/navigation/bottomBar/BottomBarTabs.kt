package com.example.navigation.bottomBar

const val HOME_ROUTE = "Home"
const val WISH_LIST_ROUTE = "WatchList"

sealed class BottomBarTabs(
    val route: String,
    val name:String
) {
    data object Home : BottomBarTabs(HOME_ROUTE,"Home" )
    data object WatchList : BottomBarTabs(WISH_LIST_ROUTE, "WatchList")
}