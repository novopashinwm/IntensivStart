package ru.androidschool.intensiv.ui.feed

import androidx.annotation.StringRes
import ru.androidschool.intensiv.data.MoviesResponse

data class FeedItem(
    @StringRes val title: Int,
    val items: MoviesResponse,
    val first: Boolean = false
)
