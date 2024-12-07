package com.example.home.data.model

import com.example.home.domain.mapper.MovieUI
import com.example.home.domain.mapper.toMovieEntity
import com.example.home.domain.mapper.toMovieUI

fun MovieResponse.toPaginatedMovieEntity():PaginatedMovieEntity{
    return PaginatedMovieEntity(
        id = page ?: 0,
        movieList = toMovieUI().map { it.toMovieEntity() },
    )

}

fun PaginatedMovieEntity.toMovieUiList():List<MovieUI>{
    return movieList?.map { it.toMovieUI() }?: listOf()
}
