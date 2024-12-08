package com.example.feature.home.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.home.model.HomeUIState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie_data.domain.mapper.MovieUI
import com.example.home.ui.screens.views.MovieListView
import com.example.feature.home.ui.screens.views.VerticalMovieListView

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
    val moviesFlow = state.moviesList.collectAsLazyPagingItems()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if(state.popularMoviesList.isNotEmpty())
            Text(
                text = "Popular Movies",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        MovieListView(
            movies = state.popularMoviesList,
            onFavouriteClicked = onFavouriteClicked,
            onMovieClicked = onMovieClicked
        )
        if(moviesFlow.itemCount!=0)
            Text(
                text = "All Movies",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
        VerticalMovieListView(
            movies = moviesFlow,
            onMovieClicked = onMovieClicked
        )
    }
}