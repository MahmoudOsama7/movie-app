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
fun HomeScreen(viewModel: HomeScreenViewModel){

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onAppear()
    }

    HomeScreenContent(
        state=state.value,
        onMovieClick=viewModel::onMovieClick
    )
}

@Composable
fun HomeScreenContent(
    state:HomeUIState,
    onMovieClick:(MovieUI)->Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MovieListView(
            movies = state.moviesList,
            onMovieClick=onMovieClick
        )
    }
}