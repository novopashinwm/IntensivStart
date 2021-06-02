package ru.androidschool.intensiv.ui.tvshows

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R

class TVShowContainer(
    private val items: List<Item>
) : Item() {

    override fun getLayout() = R.layout.tv_shows_fragment

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.tv_show_recycler_view.adapter =
            GroupAdapter<GroupieViewHolder>().apply { addAll(items) }
    }
}
