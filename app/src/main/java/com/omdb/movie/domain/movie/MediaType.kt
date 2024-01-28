package com.omdb.movie.domain.movie

enum class MediaType(val type: String) {
    MOVIE("movie"), SERIES("series"), EPISODE("episode");

    companion object {
        fun fromType(type: String): MediaType {
            return entries.find { it.type == type } ?: throw Error("Couldn't find Media Type")
        }
    }
}