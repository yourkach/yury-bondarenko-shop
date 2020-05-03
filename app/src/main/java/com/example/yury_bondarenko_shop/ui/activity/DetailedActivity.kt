package com.example.yury_bondarenko_shop.ui
.activity

import android.os.Bundle
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.getStrikethroughSpannable
import com.example.yury_bondarenko_shop.presenter.DetailedView
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.MvpAppCompatActivity

class DetailedActivity : MvpAppCompatActivity(), DetailedView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        setListeners()
    }

    private fun setListeners() {
        productBackIv.setOnClickListener {
            finish()
        }
    }

    override fun setProductPrice(mainPrice: String) {
        detailedMainPriceTv.text = mainPrice
    }

    override fun setProductPrice(discountPrice: String, rawPrice: String) {
        detailedMainPriceTv.text = discountPrice
        detailedRawPriceTv.text = getStrikethroughSpannable(rawPrice)
    }

    override fun setProductName(name: String) {
        detailedNameTv.text = name
    }
}
