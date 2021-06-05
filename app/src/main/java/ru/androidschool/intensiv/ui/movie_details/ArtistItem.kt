package ru.androidschool.intensiv.ui.movie_details

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor.*
import kotlinx.android.synthetic.main.item_with_text.image_preview
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Artist

class ArtistItem (
    private val content: Artist
) : Item() {

    override fun getLayout() = R.layout.item_actor

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.FIO.text = content.fullname
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500${content.avatar}")
            .into(viewHolder.image_preview)
    }
}