package com.example.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.home.data.model.MovieEntity
import com.example.home.data.model.PaginatedMovieEntity

const val MOVIE_DB = "movies_db"
@Database(
    entities = [MovieEntity::class,PaginatedMovieEntity::class],
    version = 19
)
@TypeConverters(MovieEntityTypeConverter::class)

abstract class MovieDB : RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
}