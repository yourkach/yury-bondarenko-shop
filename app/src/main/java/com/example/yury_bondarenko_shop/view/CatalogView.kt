package com.example.yury_bondarenko_shop.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoriesList(categories: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun removeItem(pos: Int)
}