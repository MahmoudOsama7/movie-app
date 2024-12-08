package com.example.movie_data.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("paginatedList")
data class PaginatedMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    val movieList:List<NormalMovieEntity>? = listOf(),
    val lastPage:Int=0
)
