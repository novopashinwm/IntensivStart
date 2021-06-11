package ru.mikhailskiy.retrofitexample.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.androidschool.intensiv.BuildConfig.THE_MOVIE_DATABASE_API


class DemoAuthenticator() : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Вызов метода авторзации
        // Сохранение
        val token = THE_MOVIE_DATABASE_API

        return response.request.newBuilder()
            .removeHeader("Auth-token")
            .addHeader("Auth-token", token)
            .build()
    }
}