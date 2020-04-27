package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BasketView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketItems(items: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateBasketItems()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(pos: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun addItem(pos: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun smoothScrollToPosition(pos: Int)
}