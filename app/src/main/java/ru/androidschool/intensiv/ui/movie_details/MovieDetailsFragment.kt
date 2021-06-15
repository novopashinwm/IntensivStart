package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_details_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.ArtistData
import ru.androidschool.intensiv.data.MovieDetails
import ru.androidschool.intensiv.extensions.loadImage
import ru.androidschool.intensiv.extensions.rating
import ru.mikhailskiy.retrofitexample.network.MovieApiClient
import timber.log.Timber


class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie_id = arguments?.getInt("movie_id")
        MovieApiClient.apiClient.getMovieDetails(movie_id!!).enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                lateinit var movie: MovieDetails
                response?.let {
                    it?.body()?.let {
                        movie = it
                    }
                }
                header_image_detail.loadImage(movie.backdropPath)
                title.text = movie.title
                movie_detail_rating.rating = movie.voteAverage.rating()
                overview.text = movie.overview
                studio_value.text = movie.productionCompanies.map { it.name }.joinToString(", ")
                genre_value.text = movie.genres.map { it.name }.toList().joinToString(", ")
                year_value.text = movie.releaseDate
            }

            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                Timber.e(t.toString())
            }
        })

        MovieApiClient.apiClient.getArtists(movie_id).enqueue(object : Callback<ArtistData>
            {
                override fun onResponse(call: Call<ArtistData>, response: Response<ArtistData>) {
                    lateinit var artists: ArtistData
                    response?.let {
                        it?.body()?.let {
                            artists = it
                        }
                    }
                    val artistList = artists.cast.map { ArtistItem(it) }.toList()
                    actor_items_container.adapter = adapter.apply { addAll(artistList) }
                }

                override fun onFailure(call: Call<ArtistData>, t: Throwable) {
                    Timber.e(t.toString())
                }
            })
    }
}
