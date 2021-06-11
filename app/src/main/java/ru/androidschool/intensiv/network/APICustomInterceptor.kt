package ru.androidschool.intensiv.network

import okhttp3.Interceptor
import okhttp3.Response
import ru.androidschool.intensiv.BuildConfig.THE_MOVIE_DATABASE_API


class APICustomInterceptor : Interceptor {
    override fun intercept(chain : Interceptor.Chain) : Response {
        val originalRequest = chain.request()
        val originalURL = originalRequest.url
        val modifiedURL = originalURL.newBuilder()
            .addQueryParameter("api_key", THE_MOVIE_DATABASE_API)
            .addQueryParameter("language","ru")
            .build()
        val modifiedRequest = originalRequest.newBuilder()
            .url(modifiedURL)
            .build()
        val response = chain.proceed(modifiedRequest)
        return response
    }
}