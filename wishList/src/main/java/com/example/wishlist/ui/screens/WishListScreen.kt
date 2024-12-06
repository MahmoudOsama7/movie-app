package com.example.wishlist.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.wishlist.ui.screens.views.EmptyStateView
import com.example.wishlist.ui.screens.views.WishListView

@Composable
fun WishListScreen(
    onStartAddingClicked:()->Unit,
    viewModel: WishListViewModel
){
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }
    if(state.value.wishListMovies.isEmpty())
        EmptyStateView(
            onStartAddingClicked =onStartAddingClicked
        )
    else
    WishListView(
        wishListedMovies = state.value.wishListMovies,
        onMovieClick = viewModel::onMovieClick
    )
}