package com.example.yury_bondarenko_shop.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class BasketItem(val product: Product, var count: Int = 1)