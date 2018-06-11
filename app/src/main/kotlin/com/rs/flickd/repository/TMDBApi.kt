package com.rs.flickd.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(@Query("page") page: Int): MutableLiveData<Page>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int): MutableLiveData<MovieDetailsData>

    @GET("movie/{id}/similar")
    fun getSimilarMovies(@Path("id") id: Int, @Query("page") page: Int): MutableLiveData<Page>

    @GET("search/movie/")
    fun searchMovie(@Query("query") query: String, @Query("page") page: Int): MutableLiveData<Page>

    @GET("movie/{id}/videos")
    fun getVideos(@Path("id") id: Int): MutableLiveData<VideosData>

}