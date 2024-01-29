package com.omdb.movie.domain.movie

data class MovieDetails(
    val title: String,
    val year: String? = null,
    val rated: String? = null,
    val released: String? = null,
    val runtime: String? = null,
    val genre: String? = null,
    val director: String? = null,
    val writer: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val language: String? = null,
    val country: String? = null,
    val awards: String? = null,
    val poster: String? = null,
    val ratings: List<Rating>,
    val metascore: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null,
    val imdbID: String,
    val type: String,
    val dvd: String? = null,
    val boxOffice: String? = null,
    val production: String? = null,
    val website: String? = null,
    val response: String
) {
    data class Rating(
        val source: String,
        val value: String
    )
}