package com.omdb.movie.data.util

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "58f7c212"

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}