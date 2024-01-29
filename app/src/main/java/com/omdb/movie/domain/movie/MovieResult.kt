package com.omdb.movie.domain.movie

data class MovieResult(
    val movies: List<MovieData>,
    val total: Int
) {
   companion object {
        fun empty() = MovieResult(listOf(), 0)
    }
}
