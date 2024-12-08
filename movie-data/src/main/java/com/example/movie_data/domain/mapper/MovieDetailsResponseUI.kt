package com.example.movie_data.domain.mapper

import com.example.movie_data.data.model.MovieDetailsResponse

fun MovieDetailsResponse.toMovieUI() =
    MovieUI(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        voteAverage = voteAverage,
        voteCount = voteCount,
        poster = "https://image.tmdb.org/t/p/w500".plus(posterPath),
        isWishListed = false,
        genres = genres.map { it.toGenreUI() }
    )

