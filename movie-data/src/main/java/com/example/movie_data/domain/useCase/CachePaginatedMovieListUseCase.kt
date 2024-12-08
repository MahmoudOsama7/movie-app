package com.example.home.domain.useCase

import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.domain.repository.MovieRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CachePaginatedMovieListUseCase @Inject constructor(
    private var movieRepository: MovieRepository
) {
    suspend operator fun invoke(paginatedMovieEntity: PaginatedMovieEntity) = movieRepository.insertPaginatedMoviesToDatabase(paginatedMovieEntity = paginatedMovieEntity)
}