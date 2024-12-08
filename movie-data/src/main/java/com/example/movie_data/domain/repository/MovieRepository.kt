package com.example.movie_data.domain.repository

import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.movie_data.data.model.PopularMovieEntity
import com.example.home.data.model.MovieResponse
import com.example.movie_data.data.model.NormalMovieEntity
import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.data.model.WishListedMovieEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(): Response<MovieResponse>
    suspend fun getMoviesFromWishList():Flow<List<WishListedMovieEntity>>
    suspend fun addMovieToWishList(wishListedMovieEntity: WishListedMovieEntity)
    suspend fun removeMovieFromWishList(wishListedMovieEntity: WishListedMovieEntity)
    suspend fun getMovieDetails(movieID:Int):Response<MovieDetailsResponse>
    suspend fun isMovieInWishList(movieID:Int):Boolean
    suspend fun getMovieActingCast(movieID:Int):Response<MovieCastResponse>
    suspend fun getSimilarMovies(movieID:Int):Response<MovieResponse>
    suspend fun addMovieToPopularList(popularMovieEntity: PopularMovieEntity)
    suspend fun getCachedPopularMoviesList():Flow<List<PopularMovieEntity>>
    suspend fun getMovies(page:Int,year:Int):Response<MovieResponse>
    suspend fun insertPaginatedMoviesToDatabase(paginatedMovieEntity: PaginatedMovieEntity)
    suspend fun getPaginatedMoviesFromDatabase(page:Int):Flow<PaginatedMovieEntity>
}