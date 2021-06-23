package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_details_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.ArtistData
import ru.androidschool.intensiv.data.MovieDetails
import ru.androidschool.intensiv.extensions.init
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

        var movie_id : Int = 0
        arguments?.getInt("movie_id")?.let {
            movie_id = it
        }

        MovieApiClient.apiClient.getMovieDetails(movie_id)
            .init()
            .subscribe { movie ->
                header_image_detail.loadImage(movie.backdropPath)
                title.text = movie.title
                movie_detail_rating.rating = movie.voteAverage.rating()
                overview.text = movie.overview
                studio_value.text = movie.productionCompanies.map { it.name }.joinToString(", ")
                genre_value.text = movie.genres.map { it.name }.toList().joinToString(", ")
                year_value.text = movie.releaseDate

            }

        MovieApiClient.apiClient.getArtists(movie_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {
                    t -> Timber.e(t.toString())
            }
            .subscribe { artists ->
                val artistList = artists.cast.map { ArtistItem(it) }.toList()
                actor_items_container.adapter = adapter.apply { addAll(artistList) }
            }

    }
}
