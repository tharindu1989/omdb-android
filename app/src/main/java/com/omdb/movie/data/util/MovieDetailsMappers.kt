package com.omdb.movie.data.util

import com.omdb.movie.data.remote.movie.MovieDetailsResponseDto
import com.omdb.movie.data.remote.movie.MovieRatingDto
import com.omdb.movie.domain.movie.MovieDetails

fun MovieDetailsResponseDto.Success.toMovie(): MovieDetails {
    return MovieDetails(
        title = this.title,
        year = this.year,
        rated = this.rated,
        released = this.released,
        runtime = this.runtime,
        genre = this.genre,
        director = this.director,
        writer = this.writer,
        actors = this.actors,
        plot = this.plot,
        language = this.language,
        country = this.country,
        awards = this.awards,
        poster = this.poster,
        ratings = this.ratings?.map { it.toRating() } ?: listOf(),
        metascore = this.metascore,
        imdbRating = this.imdbRating,
        imdbVotes = this.imdbVotes,
        imdbID = this.imdbID,
        type = this.type,
        dvd = this.dvd,
        boxOffice = this.boxOffice,
        production = this.production,
        website = this.website,
        response = this.response
    )
}

fun MovieRatingDto.toRating(): MovieDetails.Rating {
    return MovieDetails.Rating(
        source = this.source,
        value = this.value
    )
}