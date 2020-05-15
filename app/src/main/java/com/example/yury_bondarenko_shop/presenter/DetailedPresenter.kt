package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.commonPriceFormat
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.ViewedProductsDao
import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailedPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val viewedProductsDao: ViewedProductsDao,
    private val product: Product,
    private val launchedFromBasket: Boolean
) : BasePresenter<DetailedView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setProductInfo()
        viewedProductsDao.addProduct(product)
    }

    private fun setProductInfo() {
        viewState.setProductName(product.productName)
        viewState.setProductDescription(product.description)
        viewState.setProductAttributes(
            product.attributes.joinToString(
                separator = "\n",
                transform = {
                    it
                })
        )
        if (product.salePercent != 0) {
            viewState.setProductPrice(
                product.discountPrice.commonPriceFormat,
                product.price.commonPriceFormat
            )
        } else {
            viewState.setProductPrice(product.price.commonPriceFormat)
        }
        viewState.loadProductImage(product.imageUrl)
        if (launchedFromBasket) {
            viewState.setBasketWidgetsInvisible()
        }
    }

    fun onViewResume() {
        if (!launchedFromBasket) updateBasketItemsCount()
    }

    private fun updateBasketItemsCount() {
        val itemsCount = basketItemsDao.getItemsCount()
        if (itemsCount > 0) {
            val formattedCount = if (itemsCount < 10) itemsCount.toString() else "9+"
            viewState.setBasketCountLabelVisibility(true)
            viewState.setBasketCountText(formattedCount)
        } else {
            viewState.setBasketCountLabelVisibility(false)
        }
    }

    fun onBasketClicked() {
        viewState.startBasketActivity()
    }

    fun onAddToBasketClick(product: Product) {
        basketItemsDao.addProduct(product)
        if (!launchedFromBasket) updateBasketItemsCount()
    }
}


class DetailedPresenterFactory @Inject constructor(
    private val basketItemsDao: BasketItemsDao,
    private val viewedProductsDao: ViewedProductsDao
) {
    fun create(product: Product, launchedFromBasket: Boolean): DetailedPresenter {
        return DetailedPresenter(
            basketItemsDao,
            viewedProductsDao,
            product,
            launchedFromBasket
        )
    }
}