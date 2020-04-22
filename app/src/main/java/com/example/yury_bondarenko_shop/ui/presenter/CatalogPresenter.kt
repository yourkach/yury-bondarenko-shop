package com.example.yury_bondarenko_shop.ui.presenter

import com.example.yury_bondarenko_shop.view.CatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<CatalogView>() {

    private val categories = mutableListOf("Смартфоны", "Ноутбуки", "Телевизоры", "Часы")

    fun setData() {
        viewState.setCategoriesList(categories)
    }

    fun removeItem(category: String) {
        val pos = categories.indexOf(category)
        categories.remove(category)
        viewState.removeItem(pos)
    }

}