package com.example.home.ui.screens.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun VerticalMovieListView(
    movies: LazyPagingItems<MovieUI>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies.itemCount) { index ->
            VerticalMovieListItemView(movie = movies[index]?: MovieUI())
        }
    }
}