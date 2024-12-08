package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieEntity
import com.example.home.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RemoveMovieFromWishListUseCase @Inject constructor(
    private var movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieUI: MovieUI) = movieRepository.removeMovieFromWishList(
        movieEntity = movieUI.toMovieEntity()
    )
}