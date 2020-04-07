package com.example.yury_bondarenko_shop

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ProductView {

    private lateinit var presenter: BasketPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = BasketPresenter(
            listOf(
                Product(price = 123.5, salePercent = 30, productName = "iPhone Case")
                , Product(price = 100.0, salePercent = 15, productName = "Samsung Case")
                , Product(price = 80.0, salePercent = 10, productName = "Pixel Case")
            ), this
        )

        presenter.printBasket()
    }

    override fun print(price: Double) {
        Log.i("productView", price.toString())
    }

    override fun print(text: String) {
        Log.i("productView", text)
    }

}


