package com.example.yury_bondarenko_shop

import org.junit.Assert.assertEquals
import org.junit.Test

class IncrementTest {

    @Test
    fun doIncrementTest() {
        var i = 1
        i += 1
        assertEquals(2, i)
    }

    @Test
    fun divideTest() {
        assertEquals(4 / 2, 2)
    }
}