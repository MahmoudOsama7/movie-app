package com.example.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {

    @Query("UPDATE wishList SET isWishListed = :isWishListed WHERE id = :id")
    suspend fun updateWishListState(id: Int, isWishListed: Boolean): Int

    @Query("SELECT * FROM wishList WHERE isWishListed = 1")
    fun getMoviesFromWishList(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM wishList WHERE id = :movieID)")
    suspend fun isMovieInWishList(movieID: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToPopularList(movieEntity: MovieEntity)

    @Query("SELECT * FROM wishList WHERE isPopular = 1")
    fun getPopularMovies(): Flow<List<MovieEntity>>
}