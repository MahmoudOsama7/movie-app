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
class FetchMovieDetailsUseCase @Inject constructor(
    private var movieRepository: MovieRepository,
    private var checkIfMovieIsInWishlistUseCase: CheckIfMovieIsInWishlistUseCase
) {
    operator fun invoke(movieID:Int): Flow<Resource<MovieUI>> = flow {
        try {
            emit(Resource.loading(null))
            val response = movieRepository.getMovieDetails(movieID=movieID)
            if (response.isSuccessful) {
                var movieDetailsResponse=response.body()?.toMovieUI()
                movieDetailsResponse=movieDetailsResponse?.copy(
                    isWishListed = checkIfMovieIsInWishlistUseCase(movieDetailsResponse)
                )
                emit(Resource.success(movieDetailsResponse))
            } else {
                emit(Resource.error(response.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.message.orEmpty()))
        }
    }
}