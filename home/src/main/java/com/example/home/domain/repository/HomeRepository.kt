package com.example.home.domain.repository

import com.example.home.data.model.MovieResponse
import retrofit2.Response

interface HomeRepository {
    suspend fun getPopularMovies(): Response<MovieResponse>
}