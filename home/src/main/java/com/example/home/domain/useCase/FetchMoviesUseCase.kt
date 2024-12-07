package com.example.home.domain.useCase

import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.mapper.toMovieUI
import com.example.home.domain.repository.HomeRepository
import com.example.resource.Resource
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private var homeRepository: HomeRepository,
) {
    suspend operator  fun invoke(
        page:Int,
        year:Int
    ): Resource<List<MovieUI>> {
        try {
            val response = homeRepository.getMovies(
                page=page,
                year=year
            )
            if (response.isSuccessful) {
                val movieDetailsResponse=response.body()?.toMovieUI()
                return Resource.success(movieDetailsResponse)
            } else {
                Resource.error<List<MovieUI>>(response.message())
            }
        } catch (exception: Exception) {
            Resource.error<List<MovieUI>>(exception.message.orEmpty())
        }
        return Resource.error("Can't fetch data ")
    }
}