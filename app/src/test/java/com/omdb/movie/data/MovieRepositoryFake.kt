package com.omdb.movie.data

import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieDetails
import com.omdb.movie.domain.movie.MovieResult
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response

class MovieRepositoryFake : MovieRepository {
    var movies = movies()
    var movieDetails = movieDetails()
    var error: Exception? = null

    override suspend fun searchMovies(title: String, type: MediaType?): Response<MovieResult> {
        return error?.let {
            Response.Error(it.message ?: "Unknown Error")
        } ?: run {
            Response.Success(
                MovieResult(
                    movies = movies,
                    total = movies.size
                )
            )
        }
    }

    override suspend fun details(id: String): Response<MovieDetails> {

        return error?.let {
            Response.Error(it.message ?: "Unknown Error")
        } ?: run {
            Response.Success(
                data = movieDetails
            )
        }
    }
}