package com.example.yury_bondarenko_shop.domain

import java.text.DecimalFormat

class CheckoutPriceFormatterImpl : CheckoutPriceFormatter {
    private val currencySign: String = "₽"

    override fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###.00")
        return "${dec.format(price)} $currencySign"
    }
}
