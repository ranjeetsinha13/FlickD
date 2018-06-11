package com.rs.flickd.viewModels

import androidx.lifecycle.ViewModel
import com.rs.flickd.repository.TMDBApiServiceRepo
import javax.inject.Inject

open class TmdbViewModel @Inject constructor(open val tmdbApiServiceRepo: TMDBApiServiceRepo): ViewModel()
