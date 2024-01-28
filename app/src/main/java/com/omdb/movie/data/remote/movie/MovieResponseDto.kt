package com.omdb.movie.data.remote.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class MovieResponseDto {
    @JsonClass(generateAdapter = true)
    data class Success(
        @Json(name = "Search")
        val search: List<MovieDto>,
        @Json(name = "totalResults")
        val total: Int,
    ) : MovieResponseDto()

    @JsonClass(generateAdapter = true)
    data class Error(
        @Json(name = "Error")
        val error: String,
    ) : MovieResponseDto()

}