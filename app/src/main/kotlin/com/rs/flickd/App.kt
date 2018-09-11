package com.rs.flickd

import android.app.Activity
import android.app.Application
import com.rs.flickd.components.AppComponent
import com.rs.flickd.components.DaggerAppComponent
import com.rs.flickd.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var appComponent: AppComponent
    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }


    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }

}


