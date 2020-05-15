package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCategoriesList(categories: List<String>)

    @StateStrategyType(SkipStrategy::class)
    fun startCatalogActivity(category: RemoteCategory)

    @StateStrategyType(SkipStrategy::class)
    fun startBasketActivity()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountLabelVisibility(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setBasketCountText(text: String)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(stringResId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setViewedItems(newItems: List<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setViewedProductsVisibility(visible: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun startDetailedActivity(product: Product)

}