package com.example.yury_bondarenko_shop

import com.example.yury_bondarenko_shop.data.Product
import org.junit.Test

class ShoppingBasketTest {

    @Test
    fun example() {

    }
}

class ShoppingBasket(val itemsList: List<Product>) {

    fun calcPurchaseSum(): Double {
        return itemsList.sumByDouble { it.calcDiscountPrice() }
    }
}

interface BasketPrinter {
    fun printBasket(basket: ShoppingBasket)
}

class SimpleBasketPrinter : BasketPrinter {
    override fun printBasket(basket: ShoppingBasket) {
        println("${basket.itemsList.size} items in basket:")
        basket.itemsList.forEachIndexed { index, product ->
            println("${index + 1}:\t${product.calcDiscountPrice()}")
        }
        println("Total:\t${basket.calcPurchaseSum()}")
    }
}

class FormattedBasketPrinter(private val printer: PricePrinter) : BasketPrinter {
    override fun printBasket(basket: ShoppingBasket) {
        println("${basket.itemsList.size} items in basket:")
        basket.itemsList.forEachIndexed { index, product ->
            print("${index + 1}:\t")
            printer.print(product.calcDiscountPrice())
        }
        print("Total:\t")
        printer.print(basket.calcPurchaseSum())
    }
}