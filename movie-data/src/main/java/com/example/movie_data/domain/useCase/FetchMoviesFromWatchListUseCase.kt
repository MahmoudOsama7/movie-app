package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.movie_data.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@ViewModelScoped
class FetchMoviesFromWatchListUseCase @Inject constructor(
    private var movieRepository: MovieRepository
) {
    suspend operator fun invoke():List<MovieUI> = movieRepository.getMoviesFromWatchListCachedData().first().map { it.toMovieUI() }
}