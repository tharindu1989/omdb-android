package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MovieDetailsResponseDto {

    @JsonClass(generateAdapter = true)
    data class Success(
        @Json(name = "Title")
        val title: String,
        @Json(name = "Year")
        val year: String?,
        @Json(name = "Rated")
        val rated: String?,
        @Json(name = "Released")
        val released: String?,
        @Json(name = "Runtime")
        val runtime: String?,
        @Json(name = "Genre")
        val genre: String?,
        @Json(name = "Director")
        val director: String?,
        @Json(name = "Writer")
        val writer: String?,
        @Json(name = "Actors")
        val actors: String?,
        @Json(name = "Plot")
        val plot: String?,
        @Json(name = "Language")
        val language: String?,
        @Json(name = "Country")
        val country: String?,
        @Json(name = "Awards")
        val awards: String?,
        @Json(name = "Poster")
        val poster: String,
        @Json(name = "Ratings")
        val ratings: List<MovieRatingDto>?,
        @Json(name = "Metascore")
        val metascore: String?,
        @Json(name = "imdbRating")
        val imdbRating: String?,
        @Json(name = "imdbVotes")
        val imdbVotes: String?,
        @Json(name = "imdbID")
        val imdbID: String,
        @Json(name = "Type")
        val type: String,
        @Json(name = "DVD")
        val dvd: String?,
        @Json(name = "BoxOffice")
        val boxOffice: String?,
        @Json(name = "Production")
        val production: String?,
        @Json(name = "Website")
        val website: String?,
        @Json(name = "Response")
        val response: String
    ) : MovieDetailsResponseDto()

    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "Error")
        val error: String,
    ) : MovieDetailsResponseDto()
}