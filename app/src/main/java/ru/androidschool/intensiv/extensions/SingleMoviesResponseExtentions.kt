package ru.androidschool.intensiv.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.MovieDetails
import ru.androidschool.intensiv.data.MoviesResponse
import timber.log.Timber

fun Single<MoviesResponse>.init(): Single<MoviesResponse> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError { t -> Timber.e(t.toString()) }
}