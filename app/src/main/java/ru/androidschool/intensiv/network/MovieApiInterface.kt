package ru.mikhailskiy.retrofitexample.network



import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidschool.intensiv.data.ArtistData
import ru.androidschool.intensiv.data.MovieDetails
import ru.androidschool.intensiv.data.MoviesResponse
import ru.androidschool.intensiv.data.TVsResponse

interface MovieApiInterface {

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MoviesResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying() : Call<MoviesResponse>

    @GET("movie/upcoming")
    fun getUpComing(): Call<MoviesResponse>

    @GET("movie/popular")
    fun getPopular(): Call<MoviesResponse>

    @GET("tv/popular")
    fun getTVPopular(): Call<TVsResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movie_id:Int) : Call<MovieDetails>

    @GET("movie/{movie_id}/credits")
    fun getArtists(@Path("movie_id") movie_id: Int) : Call<ArtistData>
}
