package com.omdb.movie.data.repository

import com.omdb.movie.data.local.LocalCache
import com.omdb.movie.data.util.toMovieResponse
import com.omdb.movie.data.remote.OmdbApi
import com.omdb.movie.data.remote.movie.MovieDetailsResponseDto
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.data.util.toLocalMovie
import com.omdb.movie.data.util.toMovie
import com.omdb.movie.domain.movie.MediaType
import com.omdb.movie.domain.movie.MovieDetails
import com.omdb.movie.domain.movie.MovieResult
import com.omdb.movie.domain.rempository.MovieRepository
import com.omdb.movie.domain.util.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val omdbApi: OmdbApi,
    private val localCache: LocalCache
) : MovieRepository {

    override suspend fun searchMovies(
        title: String,
        type: MediaType?
    ): Response<MovieResult> {

        if (localCache.hasCache(title)) {
            val allItems = localCache.searchResult(title).map { it.toMovie() }
            return Response.Success(
                data = MovieResult(movies = allItems, total = allItems.size)
            )
        }
        // Fetch from remote server
        val response = omdbApi.search(title, type)
        if (response.isSuccessful) {
            val data: MovieResponseDto = response.body()!!
            if (data is MovieResponseDto.Success) {
                localCache.saveResult(title, data.search.map { it.toLocalMovie() })
                return Response.Success(data.toMovieResponse())
            }
            return Response.Success(data = MovieResult.empty())
        } else {
            val errorBody = response.errorBody()?.string()
            return Response.Error(errorBody ?: "Unknown Error")
        }
    }

    override suspend fun details(id: String): Response<MovieDetails> {

        val response = omdbApi.details(id);
        if (response.isSuccessful) {
            val data: MovieDetailsResponseDto = response.body()!!
            if (data is MovieDetailsResponseDto.Success) {
                return Response.Success(data = data.toMovie())
            }
            return Response.Error("Movie Not Found")
        } else {
            val errorBody = response.errorBody()?.string()
            return Response.Error(errorBody ?: "Unknown Error")
        }
    }
}