package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json

data class MovieResponseDto(
    @field:Json(name = "Search")
    val search: List<MovieDto>,
    @field:Json(name = "totalResults")
    val total: Int,
)