package com.omdb.movie.data.util

import com.omdb.movie.data.local.movie.MovieLocal
import com.omdb.movie.data.remote.movie.MovieDto
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieData
import com.omdb.movie.domain.movie.MovieResult

fun MovieResponseDto.Success.toMovieResponse(): MovieResult {
    return MovieResult(
        total = this.total,
        movies = this.search.map { it.toMovie() }
    )
}

fun MovieDto.toMovie(): MovieData {
    return MovieData(
        id = this.id,
        title = this.title,
        image = this.image,
        year = this.year,
        type = MediaType.fromType(this.type)
    )
}

fun MovieLocal.toMovie(): MovieData {
    return MovieData(
        id = this.id,
        title = this.title,
        image = this.image,
        year = this.year,
        type = MediaType.fromType(this.type)
    )
}

fun MovieDto.toLocalMovie(): MovieLocal {
    return MovieLocal(
        id = this.id,
        title = this.title,
        image = this.image,
        type = this.type,
        year = this.year,
    )
}