package com.example.movie_data.domain.mapper

import com.example.home.data.model.MovieResponse
import com.example.movie_data.domain.model.GenreUI

fun MovieResponse.toMovieUI():List<MovieUI>{
   return results?.map {
      MovieUI(
         id=it.id,
         originalTitle = it.originalTitle,
         overview = it.overview,
         voteCount = it.voteCount,
         voteAverage = it.voteAverage,
         poster = "https://image.tmdb.org/t/p/w500".plus(it.posterPath),
         genres = listOf(),
         popularity = it.popularity,
         releaseDate = it.releaseDate
      )
   }?: listOf()
}

data class MovieUI(
   val id:Int=0,
   val originalTitle:String="",
   val overview:String="",
   val voteAverage:Double=0.0,
   val voteCount:Int=0,
   val poster:String="",
   val isWishListed:Boolean=false,
   val genres:List<GenreUI> = listOf(),
   val isPopular:Boolean=false,
   val popularity:Double=0.0,
   val releaseDate:String=""
)
