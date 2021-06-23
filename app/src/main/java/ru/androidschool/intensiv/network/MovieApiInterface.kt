package ru.mikhailskiy.retrofitexample.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.data.ArtistData
import ru.androidschool.intensiv.data.MovieDetails
import ru.androidschool.intensiv.data.MoviesResponse

interface MovieApiInterface {

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Single<MoviesResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying() : Single<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpComing(): Single<MoviesResponse>

    @GET("movie/popular")
    fun getPopular(): Single<MoviesResponse>

    @GET("tv/popular")
    fun getTVPopular(): Single<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movie_id:Int) : Single<MovieDetails>

    @GET("movie/{movie_id}/credits")
    fun getArtists(@Path("movie_id") movie_id: Int) : Single<ArtistData>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String) : Single<MoviesResponse>
}
