package com.example.feature.wishlist.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.movie_data.domain.mapper.MovieUI
import com.example.feature.wishlist.model.WatchList
import com.example.feature.wishlist.ui.screens.views.EmptyStateView
import com.example.feature.wishlist.ui.screens.views.WatchListView

@Composable
fun WatchListScreen(
    onStartAddingClicked: () -> Unit,
    viewModel: WatchListViewModel
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }
    WatchListScreenContent(
        state = state.value,
        onStartAddingClicked = onStartAddingClicked,
        onMovieClick = viewModel::onMovieClick
    )
}


@Composable
fun WatchListScreenContent(
    state: WatchList,
    onStartAddingClicked: () -> Unit,
    onMovieClick: (MovieUI) -> Unit
) {
    if (state.watchListMovies.isEmpty())
        EmptyStateView(
            onStartAddingClicked = onStartAddingClicked
        )
    else
        WatchListView(
            wishListedMovies = state.watchListMovies,
            onMovieClick = onMovieClick
        )
}