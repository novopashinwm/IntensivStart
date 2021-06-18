package ru.androidschool.intensiv.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.movies_recycler_view
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.init
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.feed.MovieItem
import ru.mikhailskiy.retrofitexample.network.MovieApiClient

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var searchTerm : String = ""
        requireArguments().getString(KEY_SEARCH)?.let {
            searchTerm = it
        }
        search_toolbar.setText(searchTerm)
        /*
        Не понял как делать это задание (текст ниже). Можно ли дать пример?!

    Обернуть search_edit_text в метод create
    После изменения текста через emiter вызывать onNext
    Реализовать логику фильтрации вводимого слова, а именно:
        Удалить все пробелы
        Длина слова должна быть >3 символов
        Отправлять введёное слово не раньше 0.5 секунды с момента окончания ввода

        * */
        MovieApiClient.apiClient.searchMovie(searchTerm)
            .init()
            .subscribe { response ->
                val movies = response.results
                val movieList = movies.map { movie -> MovieItem(movie) {} }.toList()
                movies_recycler_view.adapter = adapter.apply { addAll(movieList) }
            }
    }
}
