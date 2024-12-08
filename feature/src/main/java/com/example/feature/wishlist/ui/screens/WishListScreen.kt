package com.example.feature.wishlist.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.movie_data.domain.mapper.MovieUI
import com.example.wishlist.model.WishListUiState
import com.example.wishlist.ui.screens.WishListViewModel
import com.example.wishlist.ui.screens.views.EmptyStateView
import com.example.wishlist.ui.screens.views.WishListView

@Composable
fun WishListScreen(
    onStartAddingClicked: () -> Unit,
    viewModel: WishListViewModel
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }
    WishListScreenContent(
        state = state.value,
        onStartAddingClicked = onStartAddingClicked,
        onMovieClick = viewModel::onMovieClick
    )
}


@Composable
fun WishListScreenContent(
    state: WishListUiState,
    onStartAddingClicked: () -> Unit,
    onMovieClick: (MovieUI) -> Unit
) {
    if (state.wishListMovies.isEmpty())
        EmptyStateView(
            onStartAddingClicked = onStartAddingClicked
        )
    else
        WishListView(
            wishListedMovies = state.wishListMovies,
            onMovieClick = onMovieClick
        )
}