package com.rs.flickd.repository

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDBApiServiceRepo @Inject constructor(private val tmdbApi: TMDBApi) {
    fun getMovieDetails(id: Int): MutableLiveData<MovieDetailsData> = tmdbApi.getMovieDetails(id)

    fun getMovieNowPlaying(page: Int): MutableLiveData<Page> = tmdbApi.getMovieNowPlaying(page)

    fun getSimilarMovies(id: Int, page: Int): MutableLiveData<Page> = tmdbApi.getSimilarMovies(id, page)

    fun getVideos(id: Int): MutableLiveData<VideosData> = tmdbApi.getVideos(id)

    fun searchMovie(query: String, page: Int): MutableLiveData<Page> = tmdbApi.searchMovie(query, page)

}