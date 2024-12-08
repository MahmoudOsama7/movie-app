package com.example.movie_data.domain.mapper

import com.example.movie_data.data.model.NormalMovieEntity

fun MovieUI.toNormalMovieEntity(): NormalMovieEntity =
    NormalMovieEntity(
        id=id,
        originalTitle=originalTitle,
        overview=overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster=poster,
        isWishListed = isWishListed,
        isPopular = isPopular,
        popularity=popularity,
        releaseDate=releaseDate
    )

fun NormalMovieEntity.toMovieUI(): MovieUI =
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
        popularity = popularity,
        releaseDate=releaseDate
    )