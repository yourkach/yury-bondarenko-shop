package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startDetailedActivity(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startBasketActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductList(products: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMessage(message: String)
}