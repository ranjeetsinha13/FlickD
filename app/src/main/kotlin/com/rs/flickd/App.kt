package com.rs.flickd

import android.app.Application
import com.rs.flickd.components.AppComponent
import com.rs.flickd.components.DaggerAppComponent
import com.rs.flickd.modules.AppModule
import com.rs.flickd.modules.DataModule
import com.rs.flickd.modules.NetworkModule
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .networkModule(NetworkModule()).build()
    }

}


