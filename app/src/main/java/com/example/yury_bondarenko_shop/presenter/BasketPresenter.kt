package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class BasketPresenter @Inject constructor(
    private val basketItemsDao: BasketItemsDao,
    private val commonPriceFormatter: CommonPriceFormatter
) : MvpPresenter<BasketView>() {

    private var basketItems: MutableList<BasketItem> = basketItemsDao.getAllItems().toMutableList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onViewPause() {
        basketItemsDao.setAllItems(basketItems)
    }

    fun onViewResume() {
        basketItems = basketItemsDao.getAllItems().toMutableList()
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onBasketItemDeleteClick(pos: Int) {
        basketItems.removeAt(pos)
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onItemCountPlusClick(pos: Int): Int {
        basketItems[pos].count += 1
        updateBasketTotalPrice()
        return basketItems[pos].count
    }

    fun onItemCountMinusClick(pos: Int): Int {
        basketItems[pos].apply {
            if (count > 1) {
                count -= 1
                updateBasketTotalPrice()
            }
        }
        return basketItems[pos].count
    }

    private fun updateBasketItemsView() {
        viewState.setNewBasketItems(basketItems.map { it.copy() })
        updateCheckoutButton()
        updateBasketTotalPrice()
    }

    fun onCheckoutClick() {
        viewState.startCheckoutActivity()
    }

    fun onItemClick(pos: Int) {
        viewState.startDetailedActivity(basketItems[pos].product)
    }

    private fun updateCheckoutButton() {
        viewState.setCheckoutButtonEnabled(basketItems.isNotEmpty())
    }

    private fun updateBasketTotalPrice() {
        val totalPrice = basketItems.sumByDouble { it.count * it.product.discountPrice }
        viewState.setTotalPriceText(formatPrice(totalPrice))
    }

    fun formatPrice(price: Double): String = commonPriceFormatter.formatPrice(price)
}

