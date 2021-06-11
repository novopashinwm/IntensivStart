package ru.androidschool.intensiv.data

import com.google.gson.annotations.SerializedName

data class TVsResponse(
    val page: Int,
    val results: List<TV>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)