package com.example.movie_data.domain.useCase

import com.example.movie_data.domain.mapper.toMovieUiList
import com.example.movie_data.domain.mapper.MovieUI
import com.example.home.domain.repository.HomeRepository
import com.example.resource.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@ViewModelScoped
class GetCachedPaginatedMoviesUseCase @Inject constructor(
    private var homeRepository: HomeRepository
) {
    suspend operator fun invoke(page:Int):Resource<List<MovieUI>>{
        return Resource.success(homeRepository.getPaginatedMoviesFromDatabase(page=page).firstOrNull()?.toMovieUiList())
    }
}