package com.example.home.domain.repository

import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.home.data.model.MovieEntity
import com.example.home.data.model.MovieResponse
import com.example.movie_data.data.model.PaginatedMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface HomeRepository {
    suspend fun getPopularMovies(): Response<MovieResponse>
    suspend fun getMoviesFromWishList():Flow<List<MovieEntity>>
    suspend fun addMovieToWishList(movieEntity: MovieEntity)
    suspend fun removeMovieFromWishList(movieEntity: MovieEntity)
    suspend fun getMovieDetails(movieID:Int):Response<MovieDetailsResponse>
    suspend fun isMovieInWishList(movieID:Int):Boolean
    suspend fun getMovieActingCast(movieID:Int):Response<MovieCastResponse>
    suspend fun getSimilarMovies(movieID:Int):Response<MovieResponse>
    suspend fun addMovieToPopularList(movieEntity: MovieEntity)
    suspend fun getCachedPopularMoviesList():Flow<List<MovieEntity>>
    suspend fun getMovies(page:Int,year:Int):Response<MovieResponse>
    suspend fun insertPaginatedMoviesToDatabase(paginatedMovieEntity: PaginatedMovieEntity)
    suspend fun getPaginatedMoviesFromDatabase(page:Int):Flow<PaginatedMovieEntity>
}