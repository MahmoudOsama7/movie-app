package com.example.movie_data.data.local

import androidx.room.TypeConverter
import com.example.home.data.model.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieEntityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMovieEntityList(value: List<MovieEntity>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMovieEntityList(value: String): List<MovieEntity> {
        val type = object : TypeToken<List<MovieEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
