package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.home.domain.repository.MovieRepository
import com.example.home.domain.useCase.CachePopularMovieUseCase
import com.example.home.domain.useCase.FetchCachedPopularMoviesUseCase
import com.example.home.domain.useCase.FetchMoviesFromWishListUseCase
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchTheFirstTenPopularMoviesUseCase @Inject constructor(
    private var movieRepository: MovieRepository,
    private var fetchMoviesFromWishListUseCase: FetchMoviesFromWishListUseCase,
    private var cachePopularMovieUseCase: CachePopularMovieUseCase,
    private var fetchCachedPopularMoviesUseCase: FetchCachedPopularMoviesUseCase
) {
    operator fun invoke(): Flow<Resource<List<MovieUI>>> = flow {
        try {
            emit(Resource.loading(null))
            val response = movieRepository.getPopularMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.toMovieUI()?.take(10)?.map {
                    val movie = it.copy(
                        isWishListed = fetchMoviesFromWishListUseCase().map { it.id }.contains(it.id),
                        isPopular = true
                    )
                    cachePopularMovieUseCase(movieUI = movie)
                    movie
                }
                emit(Resource.success(movies))
            } else {
                emit(Resource.error(
                    data = fetchCachedPopularMoviesUseCase().sortedBy { it.popularity }.reversed(),
                    message = response.message())
                )
            }
        } catch (exception: Exception) {
            emit(Resource.error(
                data = fetchCachedPopularMoviesUseCase().sortedBy { it.popularity }.reversed(),
                message = exception.message.orEmpty())
            )
        }
    }
}