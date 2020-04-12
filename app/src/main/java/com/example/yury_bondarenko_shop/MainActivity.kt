package com.example.yury_bondarenko_shop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_place_order.*

class MainActivity : AppCompatActivity(), ProductView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_place_order)


        //setting default view values
        setRawPrice("0 Р")
        setDiscount("0 Р")
        setTotalPrice("0 Р")
        setBasketItemsCount(0)
    }

    override fun print(price: Double) {
        Log.i("productView", price.toString())
    }

    override fun print(text: String) {
        Log.i("productView", text)
    }

    fun setRawPrice(formattedPrice: String) {
        rawPrice.text = formattedPrice
    }

    fun setDiscount(formattedDiscount: String) {
        discountAmount.text = formattedDiscount
    }

    fun setTotalPrice(formattedPrice: String) {
        totalPrice.text = formattedPrice
    }

    fun setBasketItemsCount(count: Int) {
        productsCount.text = "($count)"
    }

}


