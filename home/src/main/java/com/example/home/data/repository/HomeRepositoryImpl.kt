package com.example.home.data.repository

import com.example.home.data.model.MovieResponse
import com.example.home.data.remote.HomeService
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) :HomeRepository {
    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return homeService.getPopularMovies()
    }
}