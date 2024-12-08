package com.example.wishlist.ui.screens.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movie_data.domain.mapper.MovieUI

@Composable
fun WishListView(wishListedMovies: List<MovieUI>, onMovieClick: (MovieUI) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(wishListedMovies) { movieUI ->
            WishListItemView(movieUI = movieUI, onMovieClick = onMovieClick)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}