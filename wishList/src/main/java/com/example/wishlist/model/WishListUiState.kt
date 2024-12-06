package com.example.wishlist.model

import com.example.home.domain.mapper.MovieUI

data class WishListUiState(
    val wishListMovies:List<MovieUI> = listOf()
)
