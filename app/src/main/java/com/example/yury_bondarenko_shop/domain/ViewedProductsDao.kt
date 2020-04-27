package com.example.yury_bondarenko_shop.domain

import com.example.yury_bondarenko_shop.domain.model.Product

interface ViewedProductsDao {
    /**
     * save this [product] as viewed
     */
    fun addProduct(product: Product)

    /**
     * save all from [products] list as viewed
     */
    fun addProducts(products: List<Product>)

    /**
     * get all viewed products
     * might be empty
     */
    fun getAllProducts(): List<Product>
}