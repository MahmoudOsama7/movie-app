package com.example.feature.home.ui.screens.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.movie_data.domain.mapper.MovieUI
import com.example.utils.getMonthFromDate

@Composable
fun VerticalMovieListView(
    movies: LazyPagingItems<MovieUI>,
    onMovieClicked:(MovieUI)->Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies.itemCount) { index ->
            val currentItem = movies[index]
            val previousItem = if (index > 0) movies.peek(index - 1) else null
            if (currentItem?.releaseDate?.getMonthFromDate() != previousItem?.releaseDate?.getMonthFromDate()) {
                Text(
                    text = currentItem?.releaseDate?.getMonthFromDate()?:"2024-12-31",
                    modifier = Modifier.padding(start = 9.dp)
                )
            }
            VerticalMovieListItemView(
                movie = movies[index]?: MovieUI(),
                onMovieClicked=onMovieClicked
            )
        }
    }
}

