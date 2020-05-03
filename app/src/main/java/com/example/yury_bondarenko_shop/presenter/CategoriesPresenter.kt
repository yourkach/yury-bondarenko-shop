package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.ViewedProductDao
import com.example.yury_bondarenko_shop.domain.interactor.AddProductToCartUseCase
import moxy.InjectViewState
import java.text.DecimalFormat
import javax.inject.Inject


@InjectViewState
class CategoriesPresenter @Inject constructor(
    private val viewedProductDao: ViewedProductDao,
    private val mainApi: MainApi,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<CategoriesView>() {


    //test data
    private val categoriesList = listOf(
        "Смартфоны",
        "Ноутбуки",
        "Персональные компьютеры",
        "Планшеты",
        "Комплектующие",
        "Аксессуары"
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setCategoriesList(categoriesList.toList())
    }


    fun onCategoryClick(pos: Int) {
        viewState.startCatalogActivity(categoriesList[pos])
    }

    fun onBasketClick() {
        viewState.startBasketActivity()
    }

}
