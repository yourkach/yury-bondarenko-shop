package com.example.yury_bondarenko_shop.domain

import android.icu.text.NumberFormat
import java.text.DecimalFormat
import java.util.*

class CommonPriceFormatterImpl(
    private val currencySign: String
) : CommonPriceFormatter {


    override fun formatPrice(price: Double): String {
        val formatted = DecimalFormat("#,###").format(price).replace(",", " ")
        return "$formatted $currencySign"
    }
}