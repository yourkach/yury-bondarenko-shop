package com.example.yury_bondarenko_shop.ui.presenter

import com.example.yury_bondarenko_shop.data.Product
import com.example.yury_bondarenko_shop.roundTo
import com.example.yury_bondarenko_shop.view.BasketView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.text.DecimalFormat

@InjectViewState
class BasketPresenter : MvpPresenter<BasketView>() {

    private val currencySign = "₽"

    private val basketItems: MutableList<Product> = mutableListOf(
        Product(12455.0, 15, "Процессор Intel Core i5-9400F"),
        Product(11500.0, 25, "Процессор AMD Ryzen 5 3500X"),
        Product(5450.0, 0, "Материнская плата MSI B450M-A PRO MAX"),
        Product(
            17090.0,
            0,
            "Видеокарта GIGABYTE GeForce GTX 1650 SUPER 1755MHz PCI-E 3.0 4096MB 12000MHz 128 bit DVI HDMI DisplayPort HDCP WINDFORCE OC"
        ),
        Product(25150.0, 8, "Процессор AMD Ryzen 7 3700X"),
        Product(
            20890.0,
            0,
            "Видеокарта GIGABYTE GeForce GTX 1660 SUPER 1830MHz PCI-E 3.0 6144MB 14000MHz 192 bit HDMI 3xDisplayPort HDCP OC"
        ),
        Product(13590.0, 5, "Процессор AMD Ryzen 9 3950X"),
        Product(12540.0, 0, "Процессор AMD Ryzen 5 3400G"),
        Product(16890.0, 0, "Процессор AMD Ryzen Threadripper"),
        Product(5890.0, 0, "Процессор Intel Core i3 Coffee Lake"),
        Product(27000.0, 35, "Процессор Intel Core i7 Coffee Lake"),
        Product(3750.0, 0, "Процессор AMD Ryzen 3 Summit Ridge"),
        Product(40250.0, 0, "Процессор Intel Core i9-9900"),
        Product(40250.0, 0, "Процессор Intel Core i9-9900"),
        Product(4650.0, 0, "Материнская плата GIGABYTE B450M S2H (rev. 1.0)")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setBasketItems(basketItems)
    }

    fun onBasketItemDeleteClick(pos: Int) {
        basketItems.removeAt(pos)
        viewState.removeItem(pos)
    }

    fun onAddItemClick() {
        basketItems.add(
            Product(
                22500.0,
                3,
                "Видеокарта MSI GeForce GTX 1660 SUPER 1815MHz PCI-E 3.0 6144MB 14000MHz 192 bit 3xDisplayPort HDMI HDCP VENTUS XS OC"
            )
        )
        val position = basketItems.size - 1
        viewState.addItem(position)
        viewState.smoothScrollToPosition(position)
    }

    fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###")
        return "${dec.format(price)} $currencySign"
    }


}

