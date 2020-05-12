package com.example.yury_bondarenko_shop.domain

import java.text.DecimalFormat

class CommonPriceFormatterImpl :
    CommonPriceFormatter {

    private val currencySign: String = "₽"

    override fun formatPrice(price: Double): String {
        val dec = DecimalFormat("#,###")
        return "${dec.format(price)} $currencySign"
    }
}