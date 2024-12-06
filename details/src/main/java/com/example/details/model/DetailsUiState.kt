package com.example.details.model

import com.example.home.domain.mapper.CastUI
import com.example.home.domain.mapper.MovieUI


data class DetailsUiState(
    val showLoading:Boolean=false,
    val movieDetails:MovieUI= MovieUI(),
    val selectedMovieID:Int=0,
    val movieCast: List<CastUI> = listOf(),
    val similarMovies:List<MovieUI> = listOf()
)
