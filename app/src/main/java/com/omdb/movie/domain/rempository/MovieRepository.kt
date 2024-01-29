package com.omdb.movie.domain.rempository

import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieDetails
import com.omdb.movie.domain.movie.MovieResult
import com.omdb.movie.domain.util.Response

interface MovieRepository {
    suspend fun searchMovies(
        title: String, type:
        MediaType? = null
    ): Response<MovieResult>

    suspend fun details(
        id: String
    ): Response<MovieDetails>
}