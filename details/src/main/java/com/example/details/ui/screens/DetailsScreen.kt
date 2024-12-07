package com.example.details.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.details.ui.screens.views.ActingCastLazyRow
import com.example.details.ui.screens.views.BackButton
import com.example.details.ui.screens.views.MovieDetailsView
import com.example.home.domain.mapper.MovieUI
import com.example.home.ui.screens.views.MovieListView

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    onBackPressed:()->Unit,
    onSimilarMovieClicked:(MovieUI)->Unit
){
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.initArguments()
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(30.dp))
        BackButton { onBackPressed() }
        MovieDetailsView(
            movie = state.value.movieDetails,
            onWishlistToggle = viewModel::onMovieDetailsWishListClicked,
        )
        ActingCastLazyRow(state.value.movieCast)
        MovieListView(
            movies = state.value.similarMovies,
            onFavouriteClicked=viewModel::onSimilarMovieWishListClicked,
            onMovieClicked=onSimilarMovieClicked
        )
    }
}
