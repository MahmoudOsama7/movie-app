package com.example.home.model

import com.example.home.domain.mapper.MovieUI

data class HomeUIState(
    val showLoading:Boolean=false,
    val moviesList:List<MovieUI> = listOf()
)
