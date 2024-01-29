package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json

data class MovieRatingDto(
    @Json(name = "Source")
    val source: String,
    @Json(name = "Value")
    val value: String
)
