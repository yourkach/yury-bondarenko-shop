package com.example.yury_bondarenko_shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailedView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductPrice(mainPrice: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductPrice(discountPrice: String, rawPrice: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductName(name: String)

}