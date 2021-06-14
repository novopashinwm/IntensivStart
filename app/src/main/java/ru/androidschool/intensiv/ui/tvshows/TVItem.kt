package ru.androidschool.intensiv.ui.tvshows

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.extensions.loadImage
import ru.androidschool.intensiv.extensions.rating

class TVItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.name
        viewHolder.movie_rating.rating = content.voteAverage.rating()
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        viewHolder.image_preview.loadImage(content.posterPath)
    }
}