package com.example.yury_bondarenko_shop.domain

import java.text.DecimalFormat

class CheckoutPriceFormatterImpl : CheckoutPriceFormatter {
    private val currencySign: String = "₽"

    override fun formatPrice(price: Double): String {
        val formattedPrice = if (price > 0.0) DecimalFormat("#,###.00").format(price) else "0"
        return "$formattedPrice $currencySign"
    }
}
