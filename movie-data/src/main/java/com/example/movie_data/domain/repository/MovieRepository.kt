package com.example.movie_data.domain.repository

import com.example.movie_data.data.model.MovieCastResponse
import com.example.movie_data.data.model.MovieDetailsResponse
import com.example.movie_data.data.model.PopularMovieEntity
import com.example.movie_data.data.model.MovieResponse
import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.data.model.WishListedMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(): Response<MovieResponse>
    suspend fun getMoviesFromWatchListCachedData():Flow<List<WishListedMovieEntity>>
    suspend fun addMovieToWatchListCachedData(wishListedMovieEntity: WishListedMovieEntity)
    suspend fun removeMovieFromWishListCachedData(wishListedMovieEntity: WishListedMovieEntity)
    suspend fun getMovieDetails(movieID:Int):Response<MovieDetailsResponse>
    suspend fun isMovieInWatchListed(movieID:Int):Boolean
    suspend fun getMovieActingCast(movieID:Int):Response<MovieCastResponse>
    suspend fun getSimilarMovies(movieID:Int):Response<MovieResponse>
    suspend fun addMovieToPopularList(popularMovieEntity: PopularMovieEntity)
    suspend fun getCachedPopularMoviesList():Flow<List<PopularMovieEntity>>
    suspend fun getMovies(page:Int,year:Int,sortBy:String):Response<MovieResponse>
    suspend fun insertPaginatedMoviesToDatabase(paginatedMovieEntity: PaginatedMovieEntity)
    suspend fun getPaginatedMoviesFromDatabase(page:Int):Flow<PaginatedMovieEntity>
}