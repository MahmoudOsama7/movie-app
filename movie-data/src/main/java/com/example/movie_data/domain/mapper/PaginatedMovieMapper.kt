package com.example.movie_data.domain.mapper

import com.example.movie_data.data.model.MovieResponse
import com.example.movie_data.data.model.PaginatedMovieEntity

fun MovieResponse.toPaginatedMovieEntity(): PaginatedMovieEntity {
    return PaginatedMovieEntity(
        id = page ?: 0,
        movieList = toMovieUI().map { it.toNormalMovieEntity() },
    )

}

fun PaginatedMovieEntity.toMovieUiList():List<MovieUI>{
    return movieList?.map { it.toMovieUI() }?: listOf()
}
