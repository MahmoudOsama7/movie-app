package com.example.movie_data.data.model

import com.example.movie_data.data.model.Movie
import com.google.gson.annotations.SerializedName


data class MovieResponse(
    @SerializedName("page")
    val page:Int?=null,
    @SerializedName("results")
    val results: List<Movie>?=null,
    @SerializedName("total_pages")
    val totalPages: Int?=null,
    @SerializedName("total_results")
    val totalResults: Int?=null
)
