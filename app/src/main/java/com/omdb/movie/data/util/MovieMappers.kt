package com.omdb.movie.data.util

import com.omdb.movie.data.remote.movie.MovieDto
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieData
import com.omdb.movie.domain.movie.MovieResponse

fun MovieResponseDto.toMovieResponse(): MovieResponse {
    return MovieResponse(
        total = this.total,
        movies = this.search.map { it.toMovie() }
    )
}

fun MovieDto.toMovie(): MovieData {
    return MovieData(
        id = this.id,
        title = this.title,
        image = this.image,
        type = MediaType.MOVIE//MediaType.valueOf(this.type)
    )
}