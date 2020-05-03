package com.example.yury_bondarenko_shop.domain.model


data class BasketItem(val product: Product, var count: Int = 1) {

}