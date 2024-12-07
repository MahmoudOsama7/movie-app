package com.example.home.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.home.model.HomeUIState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.example.home.domain.mapper.MovieUI
import com.example.home.ui.screens.views.MovieListView

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onMovieClicked:(MovieUI)->Unit
){

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }

    HomeScreenContent(
        state=state.value,
        onFavouriteClicked=viewModel::onFavouriteClicked,
        onMovieClicked = {
            onMovieClicked(it)
        }
    )
}

@Composable
fun HomeScreenContent(
    state:HomeUIState,
    onMovieClicked:(MovieUI)->Unit,
    onFavouriteClicked:(MovieUI)->Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovieListView(
            movies = state.moviesList,
            onFavouriteClicked=onFavouriteClicked,
            onMovieClicked=onMovieClicked
        )
    }
}