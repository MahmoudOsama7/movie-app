package com.example.movie_data.domain.mapper

import com.example.movie_data.data.model.MovieDetailsResponse
import com.example.network.BuildConfig

fun MovieDetailsResponse.toMovieUI() =
    MovieUI(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        voteAverage = voteAverage,
        voteCount = voteCount,
        poster = BuildConfig.POSTER_URL.plus(posterPath),
        isWishListed = false,
        genres = genres.map { it.toGenreUI() }
    )

