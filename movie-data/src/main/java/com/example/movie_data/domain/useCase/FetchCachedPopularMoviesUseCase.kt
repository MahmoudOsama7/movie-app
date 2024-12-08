package com.example.home.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@ViewModelScoped
class FetchCachedPopularMoviesUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    suspend operator fun invoke():List<MovieUI> = homeRepository.getCachedPopularMoviesList().first().map { it.toMovieUI() }
}