package com.example.home.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("wishList")
data class MovieEntity(
    @PrimaryKey
    val id:Int?=null,
    val originalTitle:String="",
    val overview:String="",
    val voteAverage:Double=0.0,
    val voteCount:Int=0,
    val poster:String="",
    val isWishListed:Boolean=false,
    val isPopular:Boolean=false,
    val popularity:Double
)
