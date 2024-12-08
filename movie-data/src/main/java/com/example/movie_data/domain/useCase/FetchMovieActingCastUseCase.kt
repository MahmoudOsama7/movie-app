package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.toMovieCastUI
import com.example.home.domain.repository.MovieRepository
import com.example.movie_data.domain.model.MovieCastUI
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchMovieActingCastUseCase @Inject constructor(
    private var movieRepository: MovieRepository,
) {
    operator fun invoke(movieID:Int): Flow<Resource<MovieCastUI>> = flow {
        try {
            emit(Resource.loading(null))
            val response = movieRepository.getMovieActingCast(movieID=movieID)
            if (response.isSuccessful) {
                emit(Resource.success(response.body()?.toMovieCastUI()))
            } else {
                emit(Resource.error(response.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.message.orEmpty()))
        }
    }
}