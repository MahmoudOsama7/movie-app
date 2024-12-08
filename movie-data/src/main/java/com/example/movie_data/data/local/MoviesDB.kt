package com.example.movie_data.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movie_data.data.model.PopularMovieEntity
import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.data.model.WishListedMovieEntity

const val MOVIE_DB = "movies_db"
@Database(
    entities = [PopularMovieEntity::class, PaginatedMovieEntity::class,WishListedMovieEntity::class],
    version = 1
)
@TypeConverters(MovieEntityTypeConverter::class)

abstract class MovieDB : RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
}