package ru.androidschool.intensiv.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(imageUrl: String) {
    Picasso.get()
        .load("https://image.tmdb.org/t/p/w500${imageUrl}")
        .into(this)
}