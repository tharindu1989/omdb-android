package com.omdb.movie.data.remote

import com.omdb.movie.data.remote.movie.MovieDetailsResponseDto
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.domain.movie.MediaType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/")
    suspend fun search(
        @Query("s") title: String,
        @Query("t") type: MediaType? = null
    ): Response<MovieResponseDto>

    @GET("/")
    suspend fun details(
        @Query("i") id: String,
    ): Response<MovieDetailsResponseDto>
}