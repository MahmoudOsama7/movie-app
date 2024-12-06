package com.example.home.domain.mapper

import com.example.home.data.model.MovieDetailsResponse

fun MovieDetailsResponse.toMovieUI()=
    MovieUI(
        id=id,
        originalTitle =originalTitle,
        overview = overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster = "https://image.tmdb.org/t/p/w500".plus(posterPath),
        isWishListed = false,
    )