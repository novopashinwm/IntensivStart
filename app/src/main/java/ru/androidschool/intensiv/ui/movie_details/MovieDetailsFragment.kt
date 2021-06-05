package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_with_text.*
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MockDetailsRepository
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.extensions.loadImage
import ru.androidschool.intensiv.ui.feed.MainCardContainer
import ru.androidschool.intensiv.ui.feed.MovieItem
import java.util.Collections.addAll

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = MockDetailsRepository.getDetailMovie()
        header_image_detail.loadImage(movie.poster.toString())
        title.text = movie.title
        movie_detail_rating.rating = movie.rating
        overview.text = context?.getString(R.string.mock_overview)
        studio_value.text = movie.studio
        genre_value.text = movie.genre
        year_value.text = movie.year
        val newArtistList = movie.artists.map { ArtistItem(it) }.toList()
        actor_items_container.adapter = adapter.apply { addAll(newArtistList) }
    }
}
