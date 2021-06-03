package ru.androidschool.intensiv.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.androidschool.intensiv.data.Movie

class TVAdapter(private val list: List<Movie>) :
RecyclerView.Adapter<TVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TVViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}
