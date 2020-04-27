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
        val items: MutableList<Product> = mutableListOf(
            Product(
                12455.0,
                15,
                "Процессор Intel Core i5-9400F"
            ),
            Product(
                11500.0,
                25,
                "Процессор AMD Ryzen 5 3500X"
            ),
            Product(
                5450.0,
                0,
                "Материнская плата MSI B450M-A PRO MAX"
            ),
            Product(
                17090.0,
                0,
                "Видеокарта GIGABYTE GeForce GTX 1650 SUPER 1755MHz PCI-E 3.0 4096MB 12000MHz 128 bit DVI HDMI DisplayPort HDCP WINDFORCE OC"
            ),
            Product(
                25150.0,
                8,
                "Процессор AMD Ryzen 7 3700X"
            ),
            Product(
                20890.0,
                0,
                "Видеокарта GIGABYTE GeForce GTX 1660 SUPER 1830MHz PCI-E 3.0 6144MB 14000MHz 192 bit HDMI 3xDisplayPort HDCP OC"
            ),
            Product(
                13590.0,
                5,
                "Процессор AMD Ryzen 9 3950X"
            ),
            Product(
                12540.0,
                0,
                "Процессор AMD Ryzen 5 3400G"
            ),
            Product(
                16890.0,
                0,
                "Процессор AMD Ryzen Threadripper"
            ),
            Product(
                5890.0,
                0,
                "Процессор Intel Core i3 Coffee Lake"
            ),
            Product(
                27000.0,
                35,
                "Процессор Intel Core i7 Coffee Lake"
            ),
            Product(
                3750.0,
                0,
                "Процессор AMD Ryzen 3 Summit Ridge"
            ),
            Product(
                40250.0,
                0,
                "Процессор Intel Core i9-9900"
            ),
            Product(
                40250.0,
                0,
                "Процессор Intel Core i9-9900"
            ),
            Product(
                4650.0,
                0,
                "Материнская плата GIGABYTE B450M S2H (rev. 1.0)"
            )
        )
        val str = Json.stringify(Product.serializer().list, items)
        println(str)
        val list = Json.parse(Product.serializer().list, str)
        list.forEach {
            println(it.toString())
        }
        println()
        items.forEach {
            println(it.toString())
        }
        Assert.assertArrayEquals(items.toTypedArray(), list.toTypedArray())
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