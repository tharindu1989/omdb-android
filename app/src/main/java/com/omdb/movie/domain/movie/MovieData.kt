package com.omdb.movie.domain.movie

data class MovieData(
    val id: String,
    val title: String,
    val type: MediaType,
    val year: String,
    val image: String
)
