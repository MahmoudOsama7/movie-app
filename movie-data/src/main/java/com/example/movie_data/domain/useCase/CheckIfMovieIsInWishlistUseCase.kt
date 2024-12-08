package com.example.home.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.home.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CheckIfMovieIsInWishlistUseCase @Inject constructor(
    private var movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieUI: MovieUI) = movieRepository.isMovieInWishList(movieID = movieUI.id)
}