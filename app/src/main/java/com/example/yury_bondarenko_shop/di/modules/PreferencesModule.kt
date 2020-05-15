package com.example.yury_bondarenko_shop.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.yury_bondarenko_shop.data.BasketItemsDaoImpl
import com.example.yury_bondarenko_shop.data.ViewedProductDaoImpl
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.ViewedProductsDao
import dagger.Module
import dagger.Provides

@Module
class PreferencesModule {

    @Provides
    fun providePreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("data", Context.MODE_PRIVATE)

    @Provides
    fun provideBasketItems(preferences: SharedPreferences): BasketItemsDao =
        BasketItemsDaoImpl(preferences)

    @Provides
    fun provideViewedProducts(preferences: SharedPreferences): ViewedProductsDao =
        ViewedProductDaoImpl(preferences)
}