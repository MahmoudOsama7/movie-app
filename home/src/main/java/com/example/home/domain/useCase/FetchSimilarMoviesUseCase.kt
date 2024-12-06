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
class FetchSimilarMoviesUseCase @Inject constructor(
    private var homeRepository: HomeRepository,
    private var fetchMoviesFromWishListUseCase: FetchMoviesFromWishListUseCase
) {
    operator fun invoke(movieID:Int): Flow<Resource<List<MovieUI>>> = flow {
        try {
            emit(Resource.loading(null))
            val response = homeRepository.getSimilarMovies(movieID=movieID)
            if (response.isSuccessful) {
                val movies = response.body()?.toMovieUI()?.take(10)?.map {
                    it.copy(
                        isWishListed = fetchMoviesFromWishListUseCase().contains(it)
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