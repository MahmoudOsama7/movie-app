package com.example.feature.home.model

import androidx.paging.PagingData
import com.example.movie_data.domain.mapper.MovieUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class HomeUIState(
    val showLoading:Boolean=false,
    val popularMoviesList:List<MovieUI> = listOf(),
    val moviesList: Flow<PagingData<MovieUI>> = flowOf()
)
