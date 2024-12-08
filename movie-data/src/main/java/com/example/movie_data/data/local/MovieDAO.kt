package com.example.movie_data.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movie_data.data.model.PopularMovieEntity
import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.data.model.WishListedMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToWishList(wishListedMovieEntity: WishListedMovieEntity)

    @Query("DELETE FROM wishList WHERE id = :id")
    suspend fun removeMovieFromWishListById(id: Int)

    @Query("SELECT * FROM wishList")
    fun getMoviesFromWishList(): Flow<List<WishListedMovieEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM wishList WHERE id = :movieID)")
    suspend fun isMovieInWishList(movieID: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToPopularList(popularMovieEntity: PopularMovieEntity)

    @Query("SELECT * FROM popular WHERE isPopular = 1")
    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPaginatedMovie(paginatedMovieEntity: PaginatedMovieEntity)

    @Query("SELECT * FROM paginatedList WHERE id = :page")
    fun getPaginatedMovies(page: Int): Flow<PaginatedMovieEntity>
}