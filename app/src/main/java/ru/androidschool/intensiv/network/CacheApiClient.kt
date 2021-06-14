package ru.mikhailskiy.retrofitexample.network

import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidschool.intensiv.RetroApp
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


object CacheApiClient {

    private const val cacheSize: Long = 5 * 1024 * 1024 // 5 MB
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    private val cache by lazy {
        Cache(File(RetroApp.instance?.cacheDir, "someIdentifier"), cacheSize)
    }

    private fun okHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .cache(cache)
            // Используется всегда
            .addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            })
            .addNetworkInterceptor(networkInterceptor()) // Используется только если сеть есть
            .addInterceptor(offlineInterceptor())
            .build()
    }

    val apiClient: MovieApiInterface by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return@lazy retrofit.create(MovieApiInterface::class.java)
    }

    private fun offlineInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Timber.d( "offline interceptor: called.")
                var request: Request = chain.request()

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!RetroApp.hasNetwork()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }

    private fun networkInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                Timber.d("network interceptor: called.")

                val response: Response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                    .maxAge(5, TimeUnit.SECONDS)
                    .build()

                return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }
    }

}
