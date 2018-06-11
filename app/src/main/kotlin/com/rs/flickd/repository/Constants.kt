package com.rs.flickd.repository

import com.rs.flickd.BuildConfig

object Constants {

    const val HTTP_CACHE_SIZE = 10 * 1024 * 1024

    val TMDB_API_TIMEOUT_CONNECT: Long = if (BuildConfig.DEBUG) 1 else 5

    val TMDB_API_TIMEOUT_READ: Long = if (BuildConfig.DEBUG) 1 else 5
}
