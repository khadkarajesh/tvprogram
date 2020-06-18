package com.nepninja.tvprogram

import android.app.Application
import com.nepninja.tvprogram.data.remote.Api
import com.nepninja.tvprogram.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            viewModel {
                MainViewModel(get(), get())
            }
            single { Api.retrofitService }
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule))
        }
    }
}