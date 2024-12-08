package com.example.movie_data.data.remote

import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY="ecbbdd15ebc92cd950aa05bcd6872e17"
interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String= API_KEY,
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActingCast(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieCastResponse>

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovies(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String= API_KEY,
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("primary_release_year") year: Int,
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("page") page: Int
    ): Response<MovieResponse>

}