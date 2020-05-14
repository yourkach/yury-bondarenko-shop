package com.example.yury_bondarenko_shop

import android.text.SpannableString
import android.text.style.StrikethroughSpan

/**
 * returns spannable with strikethrough text
 */
val String.strikethroughSpannable: SpannableString
    get() {
        val spannable = SpannableString(this)
        spannable.setSpan(StrikethroughSpan(), 0, this.length, 0)
        return spannable
    }

