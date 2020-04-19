package com.example.yury_bondarenko_shop.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProductView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(price: Double)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun print(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorLastName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorFirstName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorMiddleName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorPhone(visible: Boolean)
}