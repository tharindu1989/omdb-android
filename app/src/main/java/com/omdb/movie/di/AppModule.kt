package com.omdb.movie.di

import android.util.Log
import com.omdb.movie.data.local.LocalCache
import com.omdb.movie.data.remote.OmdbApi
import com.omdb.movie.data.remote.movie.MovieResponseDto
import com.omdb.movie.data.util.ApiInterceptor
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalCache(): LocalCache {
        return LocalCache()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideOmdbApi(client: OkHttpClient): OmdbApi {

        val vehicleFactory =
            PolymorphicJsonAdapterFactory.of(MovieResponseDto::class.java, "Response")
                .withSubtype(MovieResponseDto.Success::class.java, "True")
                .withSubtype(MovieResponseDto.Error::class.java, "False")

        val moshi = Moshi.Builder()
            .add(vehicleFactory)
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(OmdbApi::class.java)
    }


}