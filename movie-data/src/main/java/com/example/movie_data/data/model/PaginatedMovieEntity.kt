package com.example.movie_data.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.home.data.model.MovieEntity

@Entity("paginatedList")
data class PaginatedMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=0,
    val movieList:List<MovieEntity>? = listOf(),
    val lastPage:Int=0
)
