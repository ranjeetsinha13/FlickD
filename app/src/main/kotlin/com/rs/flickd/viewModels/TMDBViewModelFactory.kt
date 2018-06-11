package com.rs.flickd.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rs.flickd.repository.TMDBApiServiceRepo
import javax.inject.Inject

class TMDBViewModelFactory @Inject constructor(val tmdbApiServiceRepo: TMDBApiServiceRepo) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TmdbViewModel::class.java)) {
            return TmdbViewModel(tmdbApiServiceRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}