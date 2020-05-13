package com.example.yury_bondarenko_shop.ui.adapter.basket

interface BasketItemClickCallback {
    fun onDeleteClick(pos: Int)

    /**
     * returns new count
     */
    fun onCountPlusClick(pos: Int): Int

    /**
     * returns new count
     */
    fun onCountMinusClick(pos: Int): Int

    fun onItemClick(pos: Int)
}