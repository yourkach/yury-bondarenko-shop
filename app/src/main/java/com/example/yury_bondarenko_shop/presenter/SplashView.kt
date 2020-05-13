package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SplashView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun startCategoriesActivity(categories: List<RemoteCategory>)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(stringResId: Int)

}