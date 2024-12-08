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
    viewModel: WatchListViewModel,
    onMovieClicked:(MovieUI)->Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }
    WatchListScreenContent(
        state = state.value,
        onStartAddingClicked = onStartAddingClicked,
        onMovieClicked=onMovieClicked,
        onRemoveFromWatchListClicked = viewModel::onRemoveFromWatchListClicked
    )
}


@Composable
fun WatchListScreenContent(
    state: WatchList,
    onStartAddingClicked: () -> Unit,
    onRemoveFromWatchListClicked: (MovieUI) -> Unit,
    onMovieClicked:(MovieUI)->Unit
) {
    if (state.watchListMovies.isEmpty())
        EmptyStateView(
            onStartAddingClicked = onStartAddingClicked
        )
    else
        WatchListView(
            wishListedMovies = state.watchListMovies,
            onMovieClick = onRemoveFromWatchListClicked,
            onMovieClicked=onMovieClicked
        )
}