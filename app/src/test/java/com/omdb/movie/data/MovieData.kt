package com.omdb.movie.data

import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieData
import com.omdb.movie.domain.movie.MovieDetails
import java.util.UUID

fun movie() = MovieData(
    id = UUID.randomUUID().toString(),
    year = "2026",
    type = MediaType.MOVIE,
    image = "image-url",
    title = "Test Movie"
)

fun movies() = (1..10).map { movie() }

fun movieDetails() = MovieDetails(
    title = "Movie Title",
    imdbID = "movie-id",
    type = "movie",
    ratings = listOf(),
    response = "True",
)