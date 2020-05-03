package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.ViewedProductDao
import com.example.yury_bondarenko_shop.domain.interactor.AddProductToCartUseCase
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class DetailedPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao,
    private val mainApi: MainApi,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<DetailedView>() {

    //TODO

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }
}