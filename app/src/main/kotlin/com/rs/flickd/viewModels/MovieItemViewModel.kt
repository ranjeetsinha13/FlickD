package com.rs.flickd.viewModels

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.rs.flickd.repository.Movie


class MovieItemViewModel(private val movie: Movie) : BaseObservable() {

    private val id: Int = movie.id


    val title: String
        @Bindable
        get() = movie.title


    val rating
        @Bindable
        get() = movie.voteAverage.toFloat() / 2

    //TODO(RS) : Create a Firebase service and get the favorite info from there;

    val isFavorite: Boolean
        @Bindable
        get() = false


    fun onClickItem(view: View) {
        // handle click and go to movie details Activity
    }


}