package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.movie_data.domain.repository.MovieRepository
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchSimilarMoviesUseCase @Inject constructor(
    private var movieRepository: MovieRepository,
    private var fetchMoviesFromWatchListUseCase: FetchMoviesFromWatchListUseCase
) {
    operator fun invoke(movieID:Int): Flow<Resource<List<MovieUI>>> = flow {
        try {
            emit(Resource.loading(null))
            val response = movieRepository.getSimilarMovies(movieID=movieID)
            if (response.isSuccessful) {
                val movies = response.body()?.toMovieUI()?.take(10)?.map {
                    it.copy(
                        isWishListed = fetchMoviesFromWatchListUseCase().map { it.id }.contains(it.id)
                    )
                }
                emit(Resource.success(movies))
            } else {
                emit(Resource.error(response.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.message.orEmpty()))
        }
    }
}