package com.example.home.domain.useCase

import com.example.home.data.model.PaginatedMovieEntity
import com.example.home.domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CachePaginatedMovieListUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    suspend operator fun invoke(paginatedMovieEntity: PaginatedMovieEntity) = homeRepository.insertPaginatedMoviesToDatabase(paginatedMovieEntity = paginatedMovieEntity)
}