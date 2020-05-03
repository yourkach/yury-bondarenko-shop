package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.DecimalFormat

@InjectViewState
class BasketPresenter : MvpPresenter<BasketView>() {

    private val currencySign = "₽"


    private val basketItems: MutableList<BasketItem> =
        //TODO remove test data setting
        mutableListOf(
            BasketItem(
                Product(
                    "1",
                    12455.0,
                    15,
                    "Процессор Intel Core i5-9400F"
                )
            ),
            BasketItem(
                Product(
                    "2",
                    11500.0,
                    25,
                    "Процессор AMD Ryzen 5 3500X"
                )
            ),
            BasketItem(
                Product(
                    "3",
                    5450.0,
                    0,
                    "Материнская плата MSI B450M-A PRO MAX"
                )
            ),
            BasketItem(
                Product(
                    "4",
                    17090.0,
                    0,
                    "Видеокарта GIGABYTE GeForce GTX 1650 SUPER 1755MHz PCI-E 3.0 4096MB 12000MHz 128 bit DVI HDMI DisplayPort HDCP WINDFORCE OC"
                )
            ),
            BasketItem(
                Product(
                    "5",
                    25150.0,
                    8,
                    "Процессор AMD Ryzen 7 3700X"
                )
            ),
            BasketItem(
                Product(
                    "6",
                    20890.0,
                    0,
                    "Видеокарта GIGABYTE GeForce GTX 1660 SUPER 1830MHz PCI-E 3.0 6144MB 14000MHz 192 bit HDMI 3xDisplayPort HDCP OC"
                )
            )
        )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onBasketItemDeleteClick(pos: Int) {
        basketItems.removeAt(pos)
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onItemCountPlusClick(pos: Int) {
        basketItems[pos].count += 1
        updateBasketItemsView()
        updateBasketTotalPrice()
    }

    fun onItemCountMinusClick(pos: Int) {
        basketItems[pos].apply {
            if (count > 1) {
                count -= 1
                updateBasketItemsView()
            }
        }
        updateBasketTotalPrice()
    }

    private fun updateBasketItemsView() {
        viewState.setNewBasketItems(basketItems.map { it.copy() })
        updateBasketTotalPrice()
    }

    fun onCheckoutClick() {
        viewState.startCheckoutActivity()
    }

    fun onItemClick(pos: Int) {
        //TODO("Start detailed activity")
    }

    private fun updateBasketTotalPrice() {
        val totalPrice = basketItems.sumByDouble { it.count * it.product.discountPrice }
        viewState.setTotalPriceText(formatPrice(totalPrice))
    }

    fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###")
        return "${dec.format(price)} $currencySign"
    }


}

