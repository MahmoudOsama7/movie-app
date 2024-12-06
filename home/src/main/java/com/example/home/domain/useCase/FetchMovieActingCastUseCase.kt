package com.example.home.domain.useCase

import com.example.home.domain.mapper.MovieCastUI
import com.example.home.domain.mapper.toMovieCastUI
import com.example.home.domain.repository.HomeRepository
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchMovieActingCastUseCase @Inject constructor(
    private var homeRepository: HomeRepository,
) {
    operator fun invoke(movieID:Int): Flow<Resource<MovieCastUI>> = flow {
        try {
            emit(Resource.loading(null))
            val response = homeRepository.getMovieActingCast(movieID=movieID)
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