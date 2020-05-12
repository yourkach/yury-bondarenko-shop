package com.example.yury_bondarenko_shop.domain

import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product

interface BasketItemsDao {

    /**
     * remove all items from basket
     */
    fun clearBasket()

    /**
     * add [product] to basket
     * if [product] item exists in basket then increment its count
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
     * returns basket items count
     */
    fun getItemsCount(): Int
}