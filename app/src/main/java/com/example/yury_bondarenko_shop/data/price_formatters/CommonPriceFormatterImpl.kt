package com.example.yury_bondarenko_shop.data.price_formatters

import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import java.text.DecimalFormat

class CommonPriceFormatterImpl(
    private val currencySign: String
) : CommonPriceFormatter {


    override fun formatPrice(price: Double): String {
        val formatted = DecimalFormat("#,###").format(price).replace(",", " ")
        return "$formatted $currencySign"
    }
}