package com.rs.flickd.viewModels

import androidx.lifecycle.MutableLiveData
import com.rs.flickd.repository.Page
import com.rs.flickd.repository.TMDBApiServiceRepo
import javax.inject.Inject

class TmdbNowPlayingMoviesViewModel @Inject constructor(override val tmdbApiServiceRepo: TMDBApiServiceRepo)
    : TmdbViewModel(tmdbApiServiceRepo = tmdbApiServiceRepo) {

    private var nowPlayingMovies: MutableLiveData<Page> = tmdbApiServiceRepo.getMovieNowPlaying(1)

    fun getNowPlayingMovies(): MutableLiveData<Page> = nowPlayingMovies


}