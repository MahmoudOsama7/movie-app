package com.example.home.domain.useCase

import com.example.movie_data.data.model.PaginatedMovieEntity
import com.example.movie_data.domain.mapper.toPaginatedMovieEntity
import com.example.movie_data.domain.mapper.MovieUI
import com.example.movie_data.domain.mapper.toMovieUI
import com.example.movie_data.domain.repository.MovieRepository
import com.example.resource.Resource
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private var movieRepository: MovieRepository,
    private var cachePaginatedMovieListUseCase: CachePaginatedMovieListUseCase
) {
    suspend operator  fun invoke(
        page:Int,
        year:Int
    ): Resource<List<MovieUI>> {
        try {
            val response = movieRepository.getMovies(
                page=page,
                year=year
            )
            if (response.isSuccessful) {
                val movieDetailsResponse=response.body()?.toMovieUI()
                val paginatedMovie=response.body()?.toPaginatedMovieEntity()?: PaginatedMovieEntity()
                cachePaginatedMovieListUseCase(paginatedMovie)
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