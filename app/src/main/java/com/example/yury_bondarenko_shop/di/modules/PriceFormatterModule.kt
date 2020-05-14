package com.example.yury_bondarenko_shop.di.modules

import com.example.yury_bondarenko_shop.domain.CheckoutPriceFormatter
import com.example.yury_bondarenko_shop.domain.CheckoutPriceFormatterImpl
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatterImpl
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import dagger.Module
import dagger.Provides


@Module
class PriceFormatterModule {

    private val currencySign: String = "₽"

    @Provides
    fun provideCommonPriceFormatter(): CommonPriceFormatter =
        CommonPriceFormatterImpl(currencySign)

    @Provides
    fun provideCheckoutPriceFormatter(): CheckoutPriceFormatter =
        CheckoutPriceFormatterImpl(currencySign)
}