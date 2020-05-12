package com.example.yury_bondarenko_shop.domain

interface CommonPriceFormatter {
    fun formatPrice(price: Double): String
}