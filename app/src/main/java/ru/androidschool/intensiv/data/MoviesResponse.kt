package ru.androidschool.intensiv.data

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)