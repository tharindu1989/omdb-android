package com.omdb.movie.data.remote

import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.domain.movie.MediaType
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/?s=bat")
    suspend fun search(
//        title: String,
//        type: MediaType? = null
    ): MovieResponseDto
}