package com.example.home.domain.mapper

import com.example.home.data.model.MovieResponse

fun MovieResponse.toMovieUI():List<MovieUI>{
   return results?.map {
      MovieUI(
         originalTitle = it.originalTitle,
         overview = it.overview,
         voteCount = it.voteCount,
         voteAverage = it.voteAverage,
         poster = "https://image.tmdb.org/t/p/w500".plus(it.posterPath)
      )
   }?: listOf()
}

data class MovieUI(
   val originalTitle:String="",
   val overview:String="",
   val voteAverage:Double=0.0,
   val voteCount:Int=0,
   val poster:String=""
)
