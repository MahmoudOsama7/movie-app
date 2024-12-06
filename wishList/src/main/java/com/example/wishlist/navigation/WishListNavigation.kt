package com.example.wishlist.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.wishlist.ui.screens.WishListScreen
import com.example.wishlist.ui.screens.WishListViewModel

const val WISH_LIST_ROUTE = "WishList"

fun NavGraphBuilder.wishList(
    navigateToHomeScreen:()->Unit,
    navigateToDetailsScreen:()->Unit
) {
    composable(WISH_LIST_ROUTE) {
        val viewModel:WishListViewModel= hiltViewModel()
        WishListScreen(
            viewModel=viewModel,
            onStartAddingClicked = navigateToHomeScreen
        )
    }
}