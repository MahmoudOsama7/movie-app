package com.example.home.domain.mapper

import com.example.home.data.model.MovieCastResponse

fun MovieCastResponse.toMovieCastUI():MovieCastUI=
    MovieCastUI(
        castList = castList.map { it.toCastUI() }
    )

data class MovieCastUI(
    val castList:List<CastUI> = listOf()
)











