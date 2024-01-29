package com.omdb.movie.di.adapter

import com.omdb.movie.data.remote.movie.MovieDetailsResponseDto
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

object MovieJsonAdapters {
    val MovieResponseFactory: PolymorphicJsonAdapterFactory<MovieResponseDto> =
        PolymorphicJsonAdapterFactory.of(MovieResponseDto::class.java, "Response")
            .withSubtype(MovieResponseDto.Success::class.java, "True")
            .withSubtype(MovieResponseDto.Error::class.java, "False")

    val MovieDetailsResponseFactory: PolymorphicJsonAdapterFactory<MovieDetailsResponseDto> =
        PolymorphicJsonAdapterFactory.of(MovieDetailsResponseDto::class.java, "Response")
            .withSubtype(MovieDetailsResponseDto.Success::class.java, "True")
            .withSubtype(MovieDetailsResponseDto.Error::class.java, "False")
}