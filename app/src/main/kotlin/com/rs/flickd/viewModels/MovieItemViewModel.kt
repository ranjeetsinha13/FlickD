package com.rs.flickd.viewModels

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.rs.flickd.repository.Movie


class MovieItemViewModel(val movie: Movie): BaseObservable() {

    private val id: Int = movie.id

    @Bindable
    private val title: String = movie.title

    @Bindable
    private val voteAverage = movie.voteAverage / 2

    //TODO(RS) : Create a Firebase service and get the favorite info from there;
    @Bindable
    private val isFavorite: Boolean = false

    @Bindable
    private val posterPath: String = movie.posterPath

    fun onClickItem(view: View) {
        // handle click and go to movie details Activity
    }


}