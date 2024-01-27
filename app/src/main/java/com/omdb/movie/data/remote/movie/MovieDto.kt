package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json

data class MovieDto(
    @field:Json(name = "imdbID")
    val id: String,
    @field:Json(name = "Title")
    val title: String,
    @field:Json(name = "Year")
    val year: String,
    @field:Json(name = "Type")
    val type: String,
    @field:Json(name = "Poster")
    val image: String
)
