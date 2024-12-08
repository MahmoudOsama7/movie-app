package com.example.movie_data.domain.mapper

import com.example.movie_data.data.model.Cast
import com.example.movie_data.domain.model.CastUI
import com.example.network.BuildConfig


fun Cast.toCastUI(): CastUI =
    CastUI(
        id=id?:0,
        name=name.orEmpty(),
        profilePath = BuildConfig.POSTER_URL.plus(profilePath),
        originalName=originalName.orEmpty()
    )
