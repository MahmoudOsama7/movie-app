package com.example.wishlist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val WISH_LIST_ROUTE = "wish-list-route"

fun NavGraphBuilder.wishList(
    navigateToDetailsScreen:()->Unit
) {
    composable(WISH_LIST_ROUTE) {

    }
}