package com.example.home.domain.useCase

import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.mapper.toMovieUI
import com.example.home.domain.repository.HomeRepository
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchTheFirstTenPopularMoviesUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieUI>>> = flow {
        try {
            emit(Resource.loading(null))
            val response= homeRepository.getPopularMovies()
            if(response.isSuccessful){
                emit(Resource.success(response.body()?.toMovieUI()?.take(10)))
            }else{
                emit(Resource.error(response.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.message.orEmpty()))
        }
    }
}