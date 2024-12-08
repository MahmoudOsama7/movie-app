package com.example.feature.wishlist.model

import com.example.movie_data.domain.mapper.MovieUI

data class WishListUiState(
    val wishListMovies:List<MovieUI> = listOf()
)
