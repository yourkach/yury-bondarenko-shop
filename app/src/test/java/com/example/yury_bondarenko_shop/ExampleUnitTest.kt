package com.example.yury_bondarenko_shop

import com.example.yury_bondarenko_shop.domain.model.Product
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test
import kotlin.math.pow
import kotlin.math.round

class ExampleUnitTest {

    @Test
    fun example() {

    }
}

private fun numberIsCorrect(text: String): Boolean {
    return text.matches(Regex("((\\+7|8)([0-9]){10})"))
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
     * Outputs formatted price on new console line
     * If price have not fractional part than it will be printed as integer
     * If price have fractional part than it will be rounded for 2 symbols after "."
     */
    fun print(price: Double)
}

class SimplePricePrinter : PricePrinter {
    /**
     * Outputs only formatted price on new console line
     * !!вопрос, стоит ли дублировать документацию интерфейса в документации его реализации?
     */
    override fun print(price: Double) {
        when {
            price.isWholeNumber() -> println("${price.toInt()}")
            else -> println("${price.roundTo(2)}")
        }
    }
}

class MeasuredPricePrinter(private val measure: String = "шт") : PricePrinter {
    /**
     * Outputs price in <PRICE>/<MEASURE> format on new console line
     */
    override fun print(price: Double) {
        when {
            price.isWholeNumber() -> println("${price.toInt()}/$measure")
            else -> println("${price.roundTo(2)}/$measure")
        }
    }
}

class CurrencyPricePrinter(private val currency: String = "Р") : PricePrinter {
    /**
     * Outputs price in <PRICE><CURRENCY> format on new console line
     */
    override fun print(price: Double) {
        when {
            price.isWholeNumber() -> println("${price.toInt()}$currency")
            else -> println("${price.roundTo(2)}$currency")
        }
    }
}

/**
 * Rounds Double number to [decimals] decimal places
 */
fun Double.roundTo(decimals: Int): Double {
    val multiplier = (10.0).pow(decimals)
    return round(this * multiplier) / multiplier
}

/**
 * Returns `true` if the number is whole (integer value), `false` otherwise.
 */
fun Double.isWholeNumber(): Boolean = this % 1.0 == 0.0