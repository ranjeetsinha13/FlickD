package com.rs.flickd.modules

import android.app.Application
import android.content.Context
import com.rs.flickd.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: App) {

    /**
     * Provide application context.
     */
    @Provides
    @Singleton
    fun provideAppContext() = application


}