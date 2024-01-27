package com.omdb.movie.domain.movie

data class MovieResponse(
    val movies: List<MovieData>,
    val total: Int
)
