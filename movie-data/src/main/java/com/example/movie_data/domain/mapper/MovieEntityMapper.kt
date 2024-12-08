package com.example.movie_data.domain.mapper

import com.example.home.data.model.MovieEntity

fun MovieUI.toMovieEntity():MovieEntity=
    MovieEntity(
        id=id,
        originalTitle=originalTitle,
        overview=overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster=poster,
        isWishListed = isWishListed,
        isPopular = isPopular,
        popularity=popularity
    )

fun MovieEntity.toMovieUI(): MovieUI =
    MovieUI(
        id=id?:0,
        originalTitle=originalTitle,
        overview=overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster=poster,
        isWishListed=isWishListed,
        isPopular = isPopular,
        genres = listOf(),
        popularity = popularity
    )