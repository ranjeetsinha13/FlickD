package com.rs.flickd.components

import android.app.Application
import com.rs.flickd.App
import com.rs.flickd.modules.AppModule
import com.rs.flickd.modules.DataModule
import com.rs.flickd.modules.NetworkModule
import com.rs.flickd.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class, NetworkModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)

}