import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.init
import ru.androidschool.intensiv.ui.feed.FeedFragment.Companion.KEY_SEARCH
import ru.androidschool.intensiv.ui.feed.MovieItem
import ru.mikhailskiy.retrofitexample.network.MovieApiClient
import java.util.concurrent.TimeUnit

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
        search_toolbar.onTextChangedObservable
            .filter { it.length >= 3 }
            .debounce (500, TimeUnit.MILLISECONDS)
            .subscribe{
                MovieApiClient.apiClient.searchMovie(it)
                    .init()
                    .subscribe { response ->
                        val movies = response.results
                        val movieList = movies.map { movie -> MovieItem(movie) {} }.toList()
                        movies_recycler_view.adapter = adapter.apply { addAll(movieList) }
                    }
            }
    }
}
