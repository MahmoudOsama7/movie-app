package com.example.home.data.model

data class MovieDetailsResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val vote_average: Double,
    val poster_path: String,
//    val genres: List<Genre>,
    val runtime: Int,
    val tagline: String
)
