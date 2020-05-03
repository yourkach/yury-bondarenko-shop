package com.example.yury_bondarenko_shop.presenter

import android.util.Log
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.interactor.AddProductToCartUseCase
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.ProductFactory
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.DecimalFormat
import javax.inject.Inject

@InjectViewState
class CatalogPresenter @Inject constructor(
    private val mainApi: MainApi,
    private val addProductToCartUseCase: AddProductToCartUseCase
) : BasePresenter<CatalogView>() {

    private val currencySign = "₽"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val remoteProducts = mainApi.allProducts(author = "default")
            val productFactory = ProductFactory()
            val products = remoteProducts.map { r -> productFactory.createProduct(r) }
            viewState.setProductList(products)
        }
    }

    fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###")
        return "${dec.format(price)} $currencySign"
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        when (e::class.java) {
            ConnectException::class.java -> {
                viewState.showMessage("Нет соединения с интернетом")
            }
            UnknownHostException::class.java -> {
                viewState.showMessage("Ошибка соединения с сервером")
            }
        }
    }

    fun onBasketClicked() {
        viewState.startBasketActivity()
    }

    fun addProductToCart() {
        addProductToCartUseCase.invoke()
    }

    fun onItemClicked(product: Product) {
        viewState.startDetailedActivity(product)
    }
}