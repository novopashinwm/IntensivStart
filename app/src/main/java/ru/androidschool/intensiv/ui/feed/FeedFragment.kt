package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.extensions.init
import ru.androidschool.intensiv.ui.afterTextChanged
import ru.mikhailskiy.retrofitexample.network.MovieApiClient
import timber.log.Timber
import ru.androidschool.intensiv.data.MoviesResponse as MoviesResponse

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        with(MovieApiClient.apiClient) {
            getMovieNowPlaying().init()
                .subscribe { response -> setMovies(response, R.string.recommended, true) }

            getUpComing().init()
                .subscribe { response -> setMovies(response, R.string.upcoming) }
            getPopular().init()
                .subscribe { response -> setMovies(response, R.string.popular) }
        }
    }

    private fun setMovies(response: MoviesResponse, @StringRes sectionName: Int
                          , firstList : Boolean = false) {
        val movies = response.results
        val moviesList = listOf(
            MainCardContainer(
                sectionName,
                movies.map {
                    MovieItem(it) { movie -> openMovieDetails(movie) }
                }.toList()
            )
        )
        if (firstList) {
            movies_recycler_view.adapter = adapter.apply { addAll(moviesList) }
        } else {
            adapter.apply { addAll(moviesList) }
        }
    }

    private fun openMovieDetails(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(KEY_TITLE, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()
        adapter.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {

        const val MIN_LENGTH = 3
        const val KEY_TITLE = "movie_id"
        const val KEY_SEARCH = "search"
    }
}
