package com.example.home.domain.mapper

import com.example.home.data.model.Cast


data class CastUI(
    val id:Int=0,
    val name:String="",
    val profilePath:String="",
    val originalName:String=""
)


fun Cast.toCastUI():CastUI=
    CastUI(
        id=id?:0,
        name=name.orEmpty(),
        profilePath = "https://image.tmdb.org/t/p/w500".plus(profilePath),
        originalName=originalName.orEmpty()
    )
