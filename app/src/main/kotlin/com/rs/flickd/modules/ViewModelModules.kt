package com.rs.flickd.modules

import com.rs.flickd.repository.TMDBApiServiceRepo
import com.rs.flickd.viewModels.TMDBViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(tmdbApiServiceRepo: TMDBApiServiceRepo

    ): TMDBViewModelFactory {
        return TMDBViewModelFactory(tmdbApiServiceRepo)
    }

}