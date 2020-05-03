package com.example.yury_bondarenko_shop.ui.adapter.basket

interface BasketItemClickCallback {
    fun onDeleteClick(pos: Int)

    fun onCountPlusClick(pos: Int)

    fun onCountMinusClick(pos: Int)

    fun onItemClick(pos: Int)
}