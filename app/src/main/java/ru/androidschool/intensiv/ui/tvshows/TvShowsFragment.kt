package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.util.Log
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
import ru.androidschool.intensiv.data.TVsResponse
import ru.mikhailskiy.retrofitexample.network.MovieApiClient

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
        MovieApiClient.apiClient.getTVPopular().enqueue(object : Callback<TVsResponse> {
            override fun onResponse(call: Call<TVsResponse>, response: Response<TVsResponse>) {
                val tvs = response.body()!!.results
                val tvsList =  tvs.map {TVItem(it) { } }.toList()
                tv_show_recycler_view.adapter = adapter.apply { addAll(tvsList) }
            }

            override fun onFailure(call: Call<TVsResponse>, t: Throwable) {
                Log.e("ERROR", t.toString())
            }
        })

    }
}
