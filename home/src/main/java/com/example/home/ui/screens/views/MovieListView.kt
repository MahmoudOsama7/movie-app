package com.example.home.ui.screens.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun MovieListView(
    movies: List<MovieUI>,
    onFavouriteClicked:(MovieUI)->Unit,
    onMovieClicked:(MovieUI)->Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(movies.size) { index ->
            MovieItemView(
                movie = movies[index],
                onFavouriteClicked=onFavouriteClicked,
                onMovieClicked=onMovieClicked
            )
        }
    }
}