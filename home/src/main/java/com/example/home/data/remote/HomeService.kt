package com.example.home.data.remote

import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY="ecbbdd15ebc92cd950aa05bcd6872e17"
interface HomeService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String=API_KEY,
        @Query("language") language: String = "en-US",
    ): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActingCast(
        @Path("movie_id") movieID: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieCastResponse>
}