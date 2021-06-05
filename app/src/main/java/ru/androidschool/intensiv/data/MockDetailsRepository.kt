package ru.androidschool.intensiv.data

object MockDetailsRepository {
    fun getDetailMovie() : Movie {
        val title = "Начало"
        val range = 8.3
        val artists = listOf<Artist>( Artist("/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg","Leonardo DiCaprio")
        , Artist("/psAXOYp9SBOXvg6AXzARDedNQ9P.jpg","Ken Watanabe")
        , Artist("/1NOFo2LQXUQwpTnA249AV1ro4v2.jpg","Joseph Gordon-Levitt")
        , Artist("/zChwjQ9D90fxx6cgWz5mUWHNd5b.jpg","Marion Cotillard")
        , Artist("/tp157uXeMPA5G91cfecBWO2OFzn.jpg","Elliot Page")
        )
        val movie = Movie(title, range, "", artists,"Legendary Pictures","боевик, фантастика, приключения","2010","/7SivRwOLuA6DR09zNJ9JIo14GyX.jpg")
        return movie
    }
}