package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.commonPriceFormat
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class BasketPresenter @Inject constructor(
    private val basketItemsDao: BasketItemsDao
) : MvpPresenter<BasketView>() {

    private var basketItems: MutableList<BasketItem> = basketItemsDao.getAllItems().toMutableList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateBasketItemsView()
    }

    fun onViewPause() {
        basketItemsDao.setAllItems(basketItems)
    }

    fun onViewResume() {
        basketItems = basketItemsDao.getAllItems().toMutableList()
        updateBasketItemsView()
    }

    fun onBasketItemDeleteClick(pos: Int) {
        basketItems.removeAt(pos)
        updateBasketItemsView()
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
        updateBasketEmptyMessage()
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

    private fun updateBasketEmptyMessage() {
        viewState.setEmptyMsgVisibility(basketItems.isEmpty())
    }

    private fun updateBasketTotalPrice() {
        val totalPrice = basketItems.sumByDouble { it.count * it.product.discountPrice }
        viewState.setTotalPriceText(totalPrice.commonPriceFormat)
    }
}

