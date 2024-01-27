package com.omdb.movie.data.repository

import com.omdb.movie.data.util.toMovieResponse
import com.omdb.movie.data.remote.OmdbApi
import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieResponse
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val omdbApi: OmdbApi
) : MovieRepository {
    override suspend fun searchMovies(title: String, type: MediaType?): Response<MovieResponse> {
        return try {
            Response.Success(
                data = omdbApi.search().toMovieResponse()
            )
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unknown Error");
        }
    }
}