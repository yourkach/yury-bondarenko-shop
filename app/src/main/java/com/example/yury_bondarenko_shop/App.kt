package com.example.yury_bondarenko_shop

import android.app.Application
import com.example.yury_bondarenko_shop.di.AppComponent
import com.example.yury_bondarenko_shop.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}