package com.example.yury_bondarenko_shop.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.yury_bondarenko_shop.R
import kotlinx.android.synthetic.main.activity_catalog.*

class CatalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        setListeners()
    }

    private fun setListeners() {
        catalogShowBasketBtn.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }
        catalogShowProductInfoBtn.setOnClickListener {
            startActivity(Intent(this, ProductInfoActivity::class.java))
        }
    }


    companion object {
        const val PRODUCT_ID = "PRODUCT_ID"
    }
}