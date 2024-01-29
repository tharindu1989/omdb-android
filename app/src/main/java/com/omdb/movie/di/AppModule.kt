package com.omdb.movie.di

import com.omdb.movie.data.local.LocalCache
import com.omdb.movie.data.remote.OmdbApi
import com.omdb.movie.data.util.ApiInterceptor
import com.omdb.movie.di.adapter.MovieJsonAdapters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(MovieJsonAdapters.MovieResponseFactory)
            .add(MovieJsonAdapters.MovieDetailsResponseFactory)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOmdbApi(client: OkHttpClient, moshi: Moshi): OmdbApi {
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(OmdbApi::class.java)
    }


}