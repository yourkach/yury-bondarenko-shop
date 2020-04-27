package com.example.yury_bondarenko_shop

import android.content.Context
import android.content.SharedPreferences
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.round

/**
 * Rounds Double number to [decimals] decimal places
 */
fun Double.roundTo(decimals: Int): Double {
    val multiplier = (10.0).pow(decimals)
    return round(this * multiplier) / multiplier
}


val AppCompatActivity.sharedPreferences: SharedPreferences
    get() = getSharedPreferences("data", Context.MODE_PRIVATE)

fun getStrikethroughSpannable(text: String): SpannableString {
    val spannable = SpannableString(text)
    spannable.setSpan(StrikethroughSpan(), 0, text.length, 0)
    return spannable
}