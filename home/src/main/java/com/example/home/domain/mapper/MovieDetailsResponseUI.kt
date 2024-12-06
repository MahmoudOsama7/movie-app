package com.example.home.domain.mapper

import com.example.home.data.model.MovieDetailsResponse

fun MovieDetailsResponse.toMovieDetailsUI()=
    MovieDetailsUI()


data class MovieDetailsUI(
    val id:Int=0
)