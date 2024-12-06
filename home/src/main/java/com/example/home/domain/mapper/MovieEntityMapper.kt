package com.example.home.domain.mapper

import com.example.home.data.model.MovieEntity

fun MovieUI.toMovieEntity():MovieEntity=
    MovieEntity(
        id=id,
        originalTitle=originalTitle,
        overview=overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster=poster,
        isWishListed = isWishListed
    )

fun MovieEntity.toMovieUI():MovieUI=
    MovieUI(
        id=id?:0,
        originalTitle=originalTitle,
        overview=overview,
        voteAverage=voteAverage,
        voteCount=voteCount,
        poster=poster,
        isWishListed=isWishListed
    )