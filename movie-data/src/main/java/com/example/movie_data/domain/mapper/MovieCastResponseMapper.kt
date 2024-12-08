package com.example.movie_data.domain.mapper

import com.example.home.data.model.MovieCastResponse
import com.example.movie_data.domain.model.MovieCastUI

fun MovieCastResponse.toMovieCastUI(): MovieCastUI =
    MovieCastUI(
        castList = castList.map { it.toCastUI() }
    )













