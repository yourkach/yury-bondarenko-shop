package com.example.yury_bondarenko_shop

import org.junit.Test

import kotlin.math.pow
import kotlin.math.round

class ExampleUnitTest {

    @Test
    fun example() {

        val iphoneCase = Product(price = 123.5, salePercent = 30)

        var pricePrinter: PricePrinter = FirstPricePrinter()

        val discountIphoneCasePrice = iphoneCase.calcDiscountPrice()
        pricePrinter.print(discountIphoneCasePrice)

        pricePrinter = SecondPricePrinter()
        pricePrinter.print(discountIphoneCasePrice)

        pricePrinter = ThirdPricePrinter()
        pricePrinter.print(discountIphoneCasePrice)
    }
}


class Product(
    /**
     * Must be positive
     */
    private val price: Double,
    private val salePercent: Int = 0
) {
    /**
     * @return price with applied discount determined by [salePercent]
     *
     * If [salePercent] is more than 100 than product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    fun calcDiscountPrice(): Double = price * (1 - salePercent / 100.0)
}

interface PricePrinter {

    /**
     * Outputs price in <PRICE>P format.
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class FirstPricePrinter : PricePrinter {
    override fun print(price: Double) {
        if (price % 1.0 == 0.0) println("${price.toInt()}P")
        else println("${price.roundTo(2)}P")
    }
}

class SecondPricePrinter : PricePrinter {
    override fun print(price: Double) {
        when (price % 1.0) {
            0.0 -> println("${price.toInt()}P")
            else -> println("${price.roundTo(2)}P")
        }
    }
}

class ThirdPricePrinter : PricePrinter {
    override fun print(price: Double) = when (round(price)) {
        price -> println("${price.toInt()}P")
        else -> println("${price.roundTo(2)}P")
    }
}

/**
 * Rounds Double number to [decimals] decimal places
 */
fun Double.roundTo(decimals: Int): Double {
    val multiplier = (10.0).pow(decimals)
    return round(this * multiplier) / multiplier
}