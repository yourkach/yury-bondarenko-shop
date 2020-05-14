package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailedPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val commonPriceFormatter: CommonPriceFormatter,
    private val product: Product,
    private val launchedFromBasket: Boolean
) : BasePresenter<DetailedView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setProductInfo()
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
                commonPriceFormatter.formatPrice(product.discountPrice),
                commonPriceFormatter.formatPrice(product.price)
            )
        } else {
            viewState.setProductPrice(commonPriceFormatter.formatPrice(product.price))
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
    private val commonPriceFormatter: CommonPriceFormatter
) {
    fun create(product: Product, launchedFromBasket: Boolean): DetailedPresenter {
        return DetailedPresenter(
            basketItemsDao,
            commonPriceFormatter,
            product,
            launchedFromBasket
        )
    }
}