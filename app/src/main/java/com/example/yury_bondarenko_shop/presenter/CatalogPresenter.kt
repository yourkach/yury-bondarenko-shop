package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.ViewedProductsDao
import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.DecimalFormat


@InjectViewState
class CatalogPresenter(
    private val viewedProductDao: ViewedProductsDao
) : MvpPresenter<CatalogView>() {


    private val currencySign = "₽"


    init {
        //setting up test data
        viewedProductDao.addProducts(
            listOf(
                Product(120.0, 0, "Товар 1"),
                Product(115.0, 13, "Товар 2"),
                Product(95.0, 0, "Товар 3"),
                Product(129.0, 25, "Товар 4"),
                Product(25.0, 13, "Товар 5"),
                Product(10.0, 0, "Товар 6"),
                Product(25000.0, 18, "Товар 7"),
                Product(250000.0, 4, "Товар с длинным названием"),
                Product(1500.0, 0, "Товар 8")
            )
        )
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateViewedProducts()
    }

    private fun updateViewedProducts() {
        val viewedProducts = viewedProductDao.getAllProducts()
        viewState.setViewedItemsList(viewedProducts)
    }


    fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###")
        return "${dec.format(price)} $currencySign"
    }
}