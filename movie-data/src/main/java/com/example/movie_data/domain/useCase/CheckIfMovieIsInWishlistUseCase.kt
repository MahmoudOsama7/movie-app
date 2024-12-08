package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CheckIfMovieIsInWishlistUseCase @Inject constructor(
    private var movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieUI: MovieUI):Boolean{
        return movieRepository.isMovieInWishList(movieID = movieUI.id)
    }
}