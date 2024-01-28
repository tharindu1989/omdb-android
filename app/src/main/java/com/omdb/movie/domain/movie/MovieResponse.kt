package com.omdb.movie.domain.movie

data class MovieResponse(
    val movies: List<MovieData>,
    val total: Int
) {
   companion object {
        fun empty() = MovieResponse(listOf(), 0)
    }
}
