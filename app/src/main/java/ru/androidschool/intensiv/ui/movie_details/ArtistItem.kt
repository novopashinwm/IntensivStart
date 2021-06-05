package ru.androidschool.intensiv.ui.movie_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor.*
import kotlinx.android.synthetic.main.item_with_text.image_preview
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Artist
import ru.androidschool.intensiv.extensions.loadImage

class ArtistItem(
    private val content: Artist
) : Item() {

    override fun getLayout() = R.layout.item_actor

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.fullname.text = content.fullname
        viewHolder.image_preview.loadImage(content.avatar)
    }
}