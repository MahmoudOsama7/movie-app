package com.example.movie_data.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("wishList")
data class WishListedMovieEntity(
    @PrimaryKey
    val id:Int?=null,
    val originalTitle:String="",
    val overview:String="",
    val voteAverage:Double=0.0,
    val voteCount:Int=0,
    val poster:String="",
    val isWishListed:Boolean=false,
    val isPopular:Boolean=false,
    val popularity:Double,
    val isPaginated:Boolean=false,
    val pageNumber:Int=0
)