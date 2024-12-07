package com.example.home.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("paginatedList")
data class PaginatedMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    val movieList:List<MovieEntity>? = listOf(),
    val lastPage:Int=0
)
