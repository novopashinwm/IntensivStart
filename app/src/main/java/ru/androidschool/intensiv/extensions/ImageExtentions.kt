package ru.androidschool.intensiv.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.androidschool.intensiv.BuildConfig.BASE_URL_IMAGE

fun ImageView.loadImage(imageUrl: String) {
    if (imageUrl.isEmpty())
        return

    Picasso.get()
        .load("${BASE_URL_IMAGE}${imageUrl}")
        .into(this)
}