package com.example.movie_data.domain.mapper

import com.example.home.data.model.Genre
import com.example.movie_data.domain.model.GenreUI

fun Genre.toGenreUI(): GenreUI = GenreUI(
    id = id,
    name = name
)