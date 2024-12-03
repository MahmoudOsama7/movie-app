package com.example.navigation.bottomBar

const val HOME_ROUTE = "Home"
const val WISH_LIST_ROUTE = "WishList"

sealed class BottomBarTabs(
    val route: String,
    val name:String
) {
    data object Home : BottomBarTabs(HOME_ROUTE,"Home" )
    data object WishList : BottomBarTabs(WISH_LIST_ROUTE, "WishList")
}