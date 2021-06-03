package ru.androidschool.intensiv.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_with_text.*

import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie

class TVViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_tv_show, parent, false)) {

    private var mDescription: TextView? = null
    private var mRating: RatingBar? = null
    lateinit var mImage: ImageView

    init {
        mDescription = itemView.findViewById(R.id.description)
        mRating = itemView.findViewById(R.id.movie_rating)
        mImage = itemView.findViewById(R.id.image_preview)
    }

    fun bind(movie: Movie) {
       mDescription?.text = movie.title
       mRating?.rating = movie.rating
        Picasso.get()
            .load("https://d1x7zurbps6occ.cloudfront.net/product/xlarge/557711-162211.jpg")
            .into(mImage)
    }
}
