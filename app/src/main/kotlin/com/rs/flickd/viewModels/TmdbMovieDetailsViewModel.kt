package com.rs.flickd.viewModels

import androidx.lifecycle.MutableLiveData
import com.rs.flickd.repository.MovieDetailsData
import com.rs.flickd.repository.TMDBApiServiceRepo
import javax.inject.Inject

class TmdbMovieDetailsViewModel @Inject constructor(override val tmdbApiServiceRepo: TMDBApiServiceRepo)
    : TmdbViewModel(tmdbApiServiceRepo = tmdbApiServiceRepo) {


    fun getMovieDetails(id: Int): MutableLiveData<MovieDetailsData> = tmdbApiServiceRepo.getMovieDetails(id)

}