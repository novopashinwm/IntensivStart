package ru.mikhailskiy.retrofitexample.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.network.APICustomInterceptor
import ru.mikhailskiy.retrofitexample.network.logger.CustomHttpLogging

object MovieApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(APICustomInterceptor())
        .addInterceptor(HttpLoggingInterceptor(CustomHttpLogging()).apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val apiClient: MovieApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }
}
