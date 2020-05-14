package com.example.yury_bondarenko_shop.domain

import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product

interface BasketItemsDao {

    /**
     * remove all items from basket
     */
    fun clearBasket()

    /**
     * add new BasketItem with [product] to basket
     * if BasketItem with such [product] already exists then increment its count
     */
    fun addProduct(product: Product)

    /**
     * delete old items and set new [items]
     */
    fun setAllItems(items: List<BasketItem>)

    /**
     * get all basket items
     * might be empty
     */
    fun getAllItems(): List<BasketItem>

    /**
     * returns count of unique products in basket, not sum of all counts
     */
    fun getItemsCount(): Int
}