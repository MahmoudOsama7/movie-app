package com.example.home.data.remote

import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY="ecbbdd15ebc92cd950aa05bcd6872e17"
const val LANGUAGE="en-US"
interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String=API_KEY,
        @Query("language") language: String = LANGUAGE,
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
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
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1
    ): Response<MovieResponse>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String= API_KEY,
        @Query("primary_release_year") year: Int,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int
    ): Response<MovieResponse>
}