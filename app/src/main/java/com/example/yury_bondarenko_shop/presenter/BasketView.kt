package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BasketView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setNewBasketItems(items: List<BasketItem>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun smoothScrollToPosition(pos: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTotalPriceText(text: String)

    @StateStrategyType(SkipStrategy::class)
    fun startCheckoutActivity()

    @StateStrategyType(SkipStrategy::class)
    fun startDetailedActivity(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCheckoutButtonEnabled(enabled: Boolean)
}