package com.example.yury_bondarenko_shop

import org.junit.Test

class ShoppingBasketTest {

    @Test
    fun example() {

        val basket = ShoppingBasket(
            listOf(
                Product(price = 120.0, salePercent = 22),
                Product(price = 105.0, salePercent = 17),
                Product(price = 158.8, salePercent = 3)
            )
        )

        var printer: BasketPrinter

        printer = SimpleBasketPrinter()
        printer.printBasket(basket)

        println()

        printer = FormattedBasketPrinter(SimplePricePrinter())
        printer.printBasket(basket)

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