package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoriesList(categories: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startCatalogActivity(category: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startBasketActivity()
}