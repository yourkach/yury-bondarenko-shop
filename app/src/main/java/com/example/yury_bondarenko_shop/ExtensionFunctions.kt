package com.example.yury_bondarenko_shop

import kotlin.math.pow
import kotlin.math.round

/**
 * Rounds Double number to [decimals] decimal places
 */
fun Double.roundTo(decimals: Int): Double {
    val multiplier = (10.0).pow(decimals)
    return round(this * multiplier) / multiplier
}