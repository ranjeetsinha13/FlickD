package com.rs.flickd.components

import com.rs.flickd.App
import com.rs.flickd.modules.AppModule
import com.rs.flickd.modules.DataModule
import com.rs.flickd.modules.NetworkModule
import com.rs.flickd.ui.MainActivity
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        AppModule::class, DataModule::class, NetworkModule::class))

interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        /**
         * Builder for the component.
         * @return the component instance.
         */
        fun build(): AppComponent

        /**
         * Module dependency that need to be created manually and passed in.
         * @param appModule instance of the AppModule.
         * @return the builder instance so that the call can be chained.
         */
        fun appModule(appModule: AppModule): Builder

    }
}