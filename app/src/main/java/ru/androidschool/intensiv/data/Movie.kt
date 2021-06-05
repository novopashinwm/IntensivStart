package ru.androidschool.intensiv.data

class Movie(
    var title: String? = "",
    var voteAverage: Double = 0.0,
    var overview: String? = "",
    var artists : List<Artist> = emptyList(),
    var studio : String? = "",
    var genre: String? = "",
    var year: String? = "",
    var poster: String? = ""
) {
    val rating: Float
        get() = voteAverage.div(2).toFloat()
}
