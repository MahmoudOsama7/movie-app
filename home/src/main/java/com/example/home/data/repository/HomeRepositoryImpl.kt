package com.example.home.data.repository

import com.example.home.data.local.MovieDAO
import com.example.home.data.model.MovieCastResponse
import com.example.home.data.model.MovieDetailsResponse
import com.example.home.data.model.MovieEntity
import com.example.home.data.model.MovieResponse
import com.example.home.data.model.PaginatedMovieEntity
import com.example.home.data.remote.HomeService
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService,
    private val movieDAO: MovieDAO
) :HomeRepository {
    override suspend fun getPopularMovies(): Response<MovieResponse> {
        return homeService.getPopularMovies()
    }

    override suspend fun getMoviesFromWishList(): Flow<List<MovieEntity>> {
        return movieDAO.getMoviesFromWishList()
    }

    override suspend fun addMovieToWishList(movieEntity: MovieEntity) {
        movieDAO.upsertWishListState(movieEntity=movieEntity)
    }

    override suspend fun removeMovieFromWishList(movieEntity: MovieEntity) {
        movieDAO.upsertWishListState(movieEntity=movieEntity)
    }

    override suspend fun getMovieDetails(movieID:Int): Response<MovieDetailsResponse> {
        return homeService.getMovieDetails(movieId = movieID)
    }

    override suspend fun isMovieInWishList(movieID:Int): Boolean {
        return movieDAO.isMovieInWishList(movieID=movieID)
    }

    override suspend fun getMovieActingCast(movieID: Int): Response<MovieCastResponse> {
        return homeService.getMovieActingCast(movieID=movieID)
    }

    override suspend fun getSimilarMovies(movieID: Int): Response<MovieResponse> {
        return homeService.getSimilarMovies(movieID=movieID)
    }

    override suspend fun addMovieToPopularList(movieEntity: MovieEntity) {
        return movieDAO.addMovieToPopularList(movieEntity=movieEntity)
    }

    override suspend fun getCachedPopularMoviesList(): Flow<List<MovieEntity>> {
        return movieDAO.getPopularMovies()
    }

    override suspend fun getMovies(page:Int,year:Int): Response<MovieResponse> {
        return homeService.getMovies(page=page,year=year)
    }

    override suspend fun insertPaginatedMoviesToDatabase(paginatedMovieEntity: PaginatedMovieEntity) {
        return movieDAO.addPaginatedMovie(paginatedMovieEntity=paginatedMovieEntity)
    }

    override suspend fun getPaginatedMoviesFromDatabase(page:Int): Flow<PaginatedMovieEntity> {
        return movieDAO.getPaginatedMovies(page)
    }


}