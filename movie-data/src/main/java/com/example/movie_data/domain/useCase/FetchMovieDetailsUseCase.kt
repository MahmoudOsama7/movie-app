package com.example.home.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.home.domain.repository.HomeRepository
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FetchMovieDetailsUseCase @Inject constructor(
    private var homeRepository: HomeRepository,
    private var checkIfMovieIsInWishlistUseCase: CheckIfMovieIsInWishlistUseCase
) {
    operator fun invoke(movieID:Int): Flow<Resource<MovieUI>> = flow {
        try {
            emit(Resource.loading(null))
            val response = homeRepository.getMovieDetails(movieID=movieID)
            if (response.isSuccessful) {
                var movieDetailsResponse=response.body()?.toMovieUI()
                movieDetailsResponse=movieDetailsResponse?.copy(isWishListed = checkIfMovieIsInWishlistUseCase(movieDetailsResponse))
                emit(Resource.success(movieDetailsResponse))
            } else {
                emit(Resource.error(response.message()))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.message.orEmpty()))
        }
    }
}