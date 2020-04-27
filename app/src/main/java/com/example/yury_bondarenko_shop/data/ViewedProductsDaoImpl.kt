package com.example.yury_bondarenko_shop.data

import android.content.SharedPreferences
import com.example.yury_bondarenko_shop.domain.ViewedProductsDao
import com.example.yury_bondarenko_shop.domain.model.Product
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json

class ViewedProductDaoImpl(
    private val sharedPreferences: SharedPreferences
) : ViewedProductsDao {

    private var savedProducts: List<Product>
        get() {
            val listString = sharedPreferences.getString(PRODUCTS_TAG, null)
            return listString?.let { Json.parse(Product.serializer().list, it) } ?: listOf()
        }
        set(value) {
            val newListString = Json.stringify(Product.serializer().list, value)
            sharedPreferences.edit().putString(PRODUCTS_TAG, newListString).apply()
        }

    override fun addProduct(product: Product) {
        val oldProductList: List<Product> = savedProducts
        val newProductList = mutableListOf<Product>().apply {
            add(product)
            addAll(oldProductList.filter { it != product })
        }
        savedProducts = newProductList
    }

    override fun addProducts(products: List<Product>) {
        val oldProductList: List<Product> = savedProducts
        val newProductList = mutableListOf<Product>().apply {
            addAll(products)
            addAll(oldProductList.filter { !products.contains(it) })
        }
        savedProducts = newProductList
    }

    override fun getAllProducts(): List<Product> {
        return savedProducts
    }

    companion object {
        private const val PRODUCTS_TAG = "PRODUCTS_LIST"
    }
}