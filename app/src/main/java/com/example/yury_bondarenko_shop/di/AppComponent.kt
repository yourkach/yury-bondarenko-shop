package com.example.yury_bondarenko_shop.di

import android.content.Context
import com.example.yury_bondarenko_shop.di.modules.MainApiModule
import com.example.yury_bondarenko_shop.di.modules.PreferencesModule
import com.example.yury_bondarenko_shop.di.modules.PriceFormatterModule
import com.example.yury_bondarenko_shop.ui.activity.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        PreferencesModule::class,
        MainApiModule::class,
        PriceFormatterModule::class
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

    fun inject(activity: SplashScreenActivity)
    fun inject(activity: CategoriesActivity)
    fun inject(activity: DetailedActivity)
    fun inject(activity: CatalogActivity)
    fun inject(activity: BasketActivity)
    fun inject(activity: CheckoutActivity)
}