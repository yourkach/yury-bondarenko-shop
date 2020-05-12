package com.example.yury_bondarenko_shop.di.modules

import com.example.yury_bondarenko_shop.domain.CheckoutPriceFormatter
import com.example.yury_bondarenko_shop.domain.CheckoutPriceFormatterImpl
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatterImpl
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import dagger.Module
import dagger.Provides


@Module
class PriceFormatterModule {

    @Provides
    fun provideCommonPriceFormatter(): CommonPriceFormatter = CommonPriceFormatterImpl()

    @Provides
    fun provideCheckoutPriceFormatter(): CheckoutPriceFormatter = CheckoutPriceFormatterImpl()
}