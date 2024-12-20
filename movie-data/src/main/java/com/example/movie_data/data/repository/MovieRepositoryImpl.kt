package com.example.movie_data.data.repository

import com.example.movie_data.data.local.MovieDAO
import com.example.movie_data.data.model.MovieCastResponse
import com.example.movie_data.data.model.MovieDetailsResponse
import com.example.movie_data.data.model.PopularMovieEntity
import com.example.movie_data.data.model.MovieResponse
import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.data.remote.MovieService
import com.example.movie_data.domain.repository.MovieRepository
import com.example.movie_data.data.model.WishListedMovieEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val movieDAO: MovieDAO
) : MovieRepository {
    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return movieService.getPopularMovies()
    }

    override suspend fun getMoviesFromWatchListCachedData(): Flow<List<WishListedMovieEntity>> {
        return movieDAO.getMoviesFromWatchList()
    }

    override suspend fun addMovieToWatchListCachedData(wishListedMovieEntity: WishListedMovieEntity) {
        movieDAO.addMovieToWishList(wishListedMovieEntity)
    }

    override suspend fun removeMovieFromWishListCachedData(wishListedMovieEntity: WishListedMovieEntity) {
        movieDAO.removeMovieFromWatchList(wishListedMovieEntity.id?:0)
    }

    override suspend fun getMovieDetails(movieID:Int): Response<MovieDetailsResponse> {
        return movieService.getMovieDetails(movieId = movieID)
    }

    override suspend fun isMovieInWatchListed(movieID:Int): Boolean {
        return movieDAO.isMovieInWatchList(movieID=movieID)
    }

    override suspend fun getMovieActingCast(movieID: Int): Response<MovieCastResponse> {
        return movieService.getMovieActingCast(movieID=movieID)
    }

    override suspend fun getSimilarMovies(movieID: Int): Response<MovieResponse> {
        return movieService.getSimilarMovies(movieID=movieID)
    }

    override suspend fun addMovieToPopularList(popularMovieEntity: PopularMovieEntity) {
        return movieDAO.addMovieToPopularList(popularMovieEntity=popularMovieEntity)
    }

    override suspend fun getCachedPopularMoviesList(): Flow<List<PopularMovieEntity>> {
        return movieDAO.getPopularMovies()
    }

    override suspend fun getMovies(page:Int,year:Int,sortBy:String): Response<MovieResponse> {
        return movieService.getMovies(
            page=page,
            year=year,
            sortBy = sortBy
        )
    }

    override suspend fun insertPaginatedMoviesToDatabase(paginatedMovieEntity: PaginatedMovieEntity) {
        return movieDAO.addPaginatedMovie(paginatedMovieEntity=paginatedMovieEntity)
    }

    override suspend fun getPaginatedMoviesFromDatabase(page:Int): Flow<PaginatedMovieEntity> {
        return movieDAO.getPaginatedMovies(page)
    }


}