package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.data.MoviesResponse
import ru.mikhailskiy.retrofitexample.network.MovieApiClient
import timber.log.Timber

class TvShowsFragment : Fragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MovieApiClient.apiClient.getTVPopular().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                lateinit var tvs : List<Movie>
                response.body()?.results?.let {
                    tvs = it
                }
                val tvsList =  tvs.map {TVItem(it) { } }.toList()
                tv_show_recycler_view.adapter = adapter.apply { addAll(tvsList) }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Timber.e(t.toString())
            }
        })

    }
}
