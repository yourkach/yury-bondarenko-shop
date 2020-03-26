package com.example.yury_bondarenko_shop

import org.junit.Test

import kotlin.math.pow
import kotlin.math.round

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun formatPrice(price: Double, measure: String = "шт.", discount: Int): String {
        val discountPrice = (price * (1 - discount / 100.0)).roundTo(2)
        return if (discountPrice % 1.0 == 0.0) {
            "${discountPrice.toInt()}/$measure" + if (discount == 0) "" else " (скидка $discount%)"
        } else {
            "$discountPrice/$measure" + if (discount == 0) "" else " (скидка $discount%)"
        }
    }

    /**
     * Округляет число Double до необходимого количества знаков после запятой
     */
    fun Double.roundTo(decimals: Int): Double {
        val multiplier = (10.0).pow(decimals)
        return round(this * multiplier) / multiplier
    }

    @Test
    fun addition_isCorrect() {
        print(formatPrice(154.0, measure = "шт", discount = 13))
    }
}
