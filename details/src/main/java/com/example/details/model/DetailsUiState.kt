package com.example.details.model

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.model.CastUI


data class DetailsUiState(
    val showLoading:Boolean=false,
    val movieDetails: MovieUI = MovieUI(),
    val selectedMovieID:Int=0,
    val movieCast: List<CastUI> = listOf(),
    val similarMovies:List<MovieUI> = listOf()
)
