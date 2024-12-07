package com.example.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home.data.model.MovieEntity

const val MOVIE_DB = "movies_db"
@Database(
    entities = [MovieEntity::class],
    version = 9
)

abstract class MovieDB : RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
}