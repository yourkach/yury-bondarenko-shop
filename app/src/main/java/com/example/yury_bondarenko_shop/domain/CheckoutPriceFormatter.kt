package com.example.yury_bondarenko_shop.domain

interface CheckoutPriceFormatter {
    fun formatPrice(price: Double): String
}