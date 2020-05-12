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

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startBasketActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadProductImage(imgUrl: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductDescription(description: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductAttributes(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMessage(stringResId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountLabelVisibility(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountText(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketWidgetsInvisible()
}