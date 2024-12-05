package com.example.home.ui.screens.views

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.home.domain.mapper.MovieUI

@Composable
fun MovieListView(movies: List<MovieUI>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(movies.size) { index ->
            MovieItemView(movie = movies[index])
        }
    }
}