package com.example.movie_data.domain.mapper

import com.example.home.data.model.Cast
import com.example.movie_data.domain.model.CastUI


fun Cast.toCastUI(): CastUI =
    CastUI(
        id=id?:0,
        name=name.orEmpty(),
        profilePath = "https://image.tmdb.org/t/p/w500".plus(profilePath),
        originalName=originalName.orEmpty()
    )
