package com.example.feature.wishlist.model

import com.example.movie_data.domain.mapper.MovieUI

data class WatchList(
    val watchListMovies:List<MovieUI> = listOf()
)
