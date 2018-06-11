package com.rs.flickd.repository

import com.google.gson.annotations.SerializedName


data class Page(@SerializedName("results") val moviesList: List<Movie>,
                @SerializedName("page") val page: Int,
                @SerializedName("total_results") val totalResults: Int,
                @SerializedName("total_pages") val totalPages: Int)


data class Movie(@SerializedName("vote_count") val voteCount: Int,
                 @SerializedName("id") val id: Int,
                 @SerializedName("vote_average") val voteAverage: Double,
                 @SerializedName("video") val video: Boolean,
                 @SerializedName("title") val title: String,
                 @SerializedName("popularity") val popularity: Double,
                 @SerializedName("poster_path") val posterPath: String,
                 @SerializedName("original_language") val originalLanguage: String,
                 @SerializedName("original_title") val originalTitle: String,
                 @SerializedName("genre_ids") val genreIds: List<Int>,
                 @SerializedName("backdrop_path") val backDropPath: String,
                 @SerializedName("adult") val adult: Boolean,
                 @SerializedName("overview") val overview: String,
                 @SerializedName("release_date") val releaseDate: String)

data class Genre(@SerializedName("id") val id: Int,
                 @SerializedName("name") val name: String)

//TODO(RS): There should be a way to inherit data classes
data class MovieDetailsData(@SerializedName("id") val id: Int,
                            @SerializedName("adult") val adult: String,
                            @SerializedName("backdrop_path") val backdropPath: String,
                            @SerializedName("genres") val genres: List<Genre>,
                            @SerializedName("imdb_id") val imdbId: String,
                            @SerializedName("original_language") val originalLanguage: String,
                            @SerializedName("original_title") val originalTitle: String,
                            @SerializedName("overview") val overview: String,
                            @SerializedName("poster_path") val posterPath: String,
                            @SerializedName("release_date") val releaseDate: String,
                            @SerializedName("runtime") val runtime: Int,
                            @SerializedName("vote_average") val voteAverage: Double)


data class VideosData(@SerializedName("id") val id: Int,
                      @SerializedName("results") val results: List<Video>)

data class Video(@SerializedName("id") val id: String,
                 @SerializedName("key") val key: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("site") val site: String,
                 @SerializedName("type") val type: String)



