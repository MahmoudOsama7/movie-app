package com.example.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.home.data.model.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {

    @Query("UPDATE wishList SET isWishListed = :isWishListed WHERE id = :id")
    suspend fun updateWishListState(id: Int, isWishListed: Boolean): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToWishList(movieEntity: MovieEntity)

    @Transaction
    suspend fun upsertWishListState(movieEntity: MovieEntity) {
        val rowsUpdated = updateWishListState(movieEntity.id ?: return, movieEntity.isWishListed)
        if (rowsUpdated == 0) {
            addMovieToWishList(movieEntity)
        }
    }

    @Query("SELECT * FROM wishList WHERE isWishListed = 1")
    fun getMoviesFromWishList(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM wishList WHERE id = :movieID AND isWishListed = 1)")
    suspend fun isMovieInWishList(movieID: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToPopularList(movieEntity: MovieEntity)

    @Query("SELECT * FROM wishList WHERE isPopular = 1")
    fun getPopularMovies(): Flow<List<MovieEntity>>
}