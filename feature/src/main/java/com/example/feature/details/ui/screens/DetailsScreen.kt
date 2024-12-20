package com.example.feature.details.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feature.details.model.DetailsUiState
import com.example.feature.details.ui.screens.views.ActingCastLazyRow
import com.example.designsystem.components.BackButton
import com.example.feature.details.ui.screens.views.MovieDetailsView
import com.example.movie_data.domain.mapper.MovieUI
import com.example.feature.home.ui.screens.views.MovieListView

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

    DetailsScreenContent(
        state=state.value,
        onMovieDetailsWishListClicked = viewModel::onMovieDetailsWishListClicked,
        onSimilarMovieClicked = onSimilarMovieClicked,
        onBackPressed = onBackPressed,
        onSimilarMovieWishListClicked = viewModel::onSimilarMovieWishListClicked
    )
}

@Composable
fun DetailsScreenContent(
    state: DetailsUiState,
    onBackPressed: () -> Unit,
    onMovieDetailsWishListClicked:(MovieUI)->Unit,
    onSimilarMovieWishListClicked:(MovieUI)->Unit,
    onSimilarMovieClicked:(MovieUI)->Unit
){
    if(state.movieDetails!= MovieUI())
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(30.dp))
            BackButton { onBackPressed() }
            Text(
                text = "Movie Details",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )
            MovieDetailsView(
                movie = state.movieDetails,
                onWishlistToggle = onMovieDetailsWishListClicked,
            )
            if(state.movieCast.isNotEmpty()){
                Text(
                    text = "Acting Cast",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                ActingCastLazyRow(state.movieCast)
            }
            if(state.similarMovies.isNotEmpty()){
                Text(
                    text = "Similar Movies",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
                MovieListView(
                    movies = state.similarMovies,
                    onFavouriteClicked = onSimilarMovieWishListClicked,
                    onMovieClicked = onSimilarMovieClicked
                )
            }

        }
}
