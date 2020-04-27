package com.example.yury_bondarenko_shop.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.*

@Serializable
data class Product(
    /**
     * Must be positive
     */
    val price: Double,
    val salePercent: Int = 0,
    val productName: String
) {
    /**
     * price with applied discount determined by [salePercent]
     * If [salePercent] is more than 100 then product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    val discountPrice: Double
        get() = price * (1 - salePercent / 100.0)
}


