package com.example.yury_bondarenko_shop.data

import android.content.SharedPreferences
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json

class BasketItemsDaoImpl(private val sharedPreferences: SharedPreferences) : BasketItemsDao {

    private var basketItems: List<BasketItem>
        get() {
            val listString = sharedPreferences.getString(BASKET_ITEMS_TAG, null)
            return listString?.let { Json.parse(BasketItem.serializer().list, it) } ?: listOf()
        }
        set(value) {
            val newListString = Json.stringify(BasketItem.serializer().list, value)
            sharedPreferences.edit().putString(BASKET_ITEMS_TAG, newListString).apply()
        }

    override fun clearBasket() {
        basketItems = listOf()
    }


    override fun addProduct(product: Product) {
        val items = basketItems
        val item = items.firstOrNull { it.product == product }
        if (item != null) {
            item.count += 1
            basketItems = items
        } else {
            val newItems = mutableListOf<BasketItem>().apply {
                addAll(items)
                add(BasketItem(product))
            }
            basketItems = newItems
        }
    }

    override fun setAllItems(items: List<BasketItem>) {
        basketItems = items
    }

    override fun getAllItems(): List<BasketItem> = basketItems

    override fun getItemsCount(): Int = basketItems.size

    companion object {
        private const val BASKET_ITEMS_TAG = "BASKET_ITEMS"
    }
}