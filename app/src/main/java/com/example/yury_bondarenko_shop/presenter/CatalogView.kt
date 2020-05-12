package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatalogView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun startDetailedActivity(product: Product)

    @StateStrategyType(SkipStrategy::class)
    fun startBasketActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProductList(products: List<Product>)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(stringResId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountLabelVisibility(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountText(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoryTitle(title: String)
}