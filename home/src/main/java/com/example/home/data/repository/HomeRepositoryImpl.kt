package com.example.home.data.repository

import com.example.home.data.local.MovieDAO
import com.example.home.data.model.MovieEntity
import com.example.home.data.model.MovieResponse
import com.example.home.data.remote.HomeService
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val movieDAO: MovieDAO
) :HomeRepository {
    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return homeService.getPopularMovies()
    }

    override suspend fun getMoviesFromWishList(): Flow<List<MovieEntity>> {
        return movieDAO.getMoviesFromWishList()
    }

    override suspend fun addMovieToWishList(movieEntity: MovieEntity) {
        movieDAO.addMovieToWishList(movieEntity)
    }

    override suspend fun removeMovieFromWishList(movieEntity: MovieEntity) {
        movieDAO.removeMovieFromWishList(movieEntity.id?:0)
    }
}