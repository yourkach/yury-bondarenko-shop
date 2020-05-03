package com.example.yury_bondarenko_shop.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CheckoutView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorLastName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorFirstName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorMiddleName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorPhone(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setRawPrice(formattedPrice: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setDiscount(formattedDiscount: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTotalPrice(formattedPrice: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketItemsCount(count: Int)
}