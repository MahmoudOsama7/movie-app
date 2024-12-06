package com.example.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToWishList(movieEntity: MovieEntity)

    @Query("DELETE FROM wishList WHERE id = :id")
    suspend fun removeMovieFromWishList(id: Int):Int

    @Query(value = "SELECT * FROM wishList")
    fun getMoviesFromWishList(): Flow<List<MovieEntity>>
}