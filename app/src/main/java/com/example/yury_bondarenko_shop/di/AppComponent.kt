package com.example.yury_bondarenko_shop.di

import android.content.Context
import com.example.yury_bondarenko_shop.di.modules.MainApiModule
import com.example.yury_bondarenko_shop.di.modules.PreferencesModule
import com.example.yury_bondarenko_shop.ui.activity.CatalogActivity
import com.example.yury_bondarenko_shop.ui.activity.CategoriesActivity
import com.example.yury_bondarenko_shop.ui.activity.DetailedActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(activity: CategoriesActivity)
    fun inject(activity: DetailedActivity)
    fun inject(activity: CatalogActivity)
}