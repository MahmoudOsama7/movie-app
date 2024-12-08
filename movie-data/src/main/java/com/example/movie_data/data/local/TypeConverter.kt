package com.example.movie_data.data.local

import androidx.room.TypeConverter
import com.example.movie_data.data.model.PopularMovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieEntityTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMovieEntityList(value: List<PopularMovieEntity>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMovieEntityList(value: String): List<PopularMovieEntity> {
        val type = object : TypeToken<List<PopularMovieEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
