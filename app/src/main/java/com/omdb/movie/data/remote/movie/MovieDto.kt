package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "imdbID")
    val id: String,
    @Json(name = "Title")
    val title: String,
    @Json(name = "Year")
    val year: String,
    @Json(name = "Type")
    val type: String,
    @Json(name = "Poster")
    val image: String
)
