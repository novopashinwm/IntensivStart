package ru.androidschool.intensiv.ui.movie_details

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_card_actors.*
import ru.androidschool.intensiv.R

class ArtistCardContainer(
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.item_card_actors

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.actor_items_container.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}