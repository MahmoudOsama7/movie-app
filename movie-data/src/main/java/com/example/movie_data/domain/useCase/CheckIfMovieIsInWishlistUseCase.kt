package com.example.home.domain.useCase

import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CheckIfMovieIsInWishlistUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    suspend operator fun invoke(movieUI: MovieUI) = homeRepository.isMovieInWishList(movieID = movieUI.id)
}