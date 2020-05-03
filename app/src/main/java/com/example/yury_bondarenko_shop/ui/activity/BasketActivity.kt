package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.ui.adapter.basket.BasketAdapter
import com.example.yury_bondarenko_shop.presenter.BasketPresenter
import com.example.yury_bondarenko_shop.presenter.BasketView
import com.example.yury_bondarenko_shop.ui.adapter.basket.BasketItemClickCallback
import kotlinx.android.synthetic.main.activity_basket.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class BasketActivity : MvpAppCompatActivity(),
    BasketView {


    private val presenter: BasketPresenter by moxyPresenter {
        BasketPresenter()
    }

    private var basketAdapter: BasketAdapter =
        BasketAdapter(
            object : BasketItemClickCallback {
                override fun onDeleteClick(pos: Int) {
                    presenter.onBasketItemDeleteClick(pos)
                }

                override fun onCountPlusClick(pos: Int) {
                    presenter.onItemCountPlusClick(pos)
                }

                override fun onCountMinusClick(pos: Int) {
                    presenter.onItemCountMinusClick(pos)
                }

                override fun onItemClick(pos: Int) {
                    presenter.onItemClick(pos)
                }

            },
            formatPrice = { price ->
                presenter.formatPrice(price)
            }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        setListeners()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        basketItemsRv.apply {
            layoutManager = LinearLayoutManager(this@BasketActivity)
            adapter = basketAdapter
        }
    }


    private fun setListeners() {
        basketBackIv.setOnClickListener {
            finish()
        }
        basketCheckoutBtn.setOnClickListener {
            presenter.onCheckoutClick()
        }
    }


    override fun startCheckoutActivity() {
        startActivity(Intent(this, CheckoutActivity::class.java))
    }

    override fun setNewBasketItems(items: List<BasketItem>) {
        basketAdapter.setList(items)
    }

    override fun smoothScrollToPosition(pos: Int) {
        basketItemsRv.smoothScrollToPosition(pos)
    }

    override fun setTotalPriceText(text: String) {
        basketTotalPrice.text = text
    }
}
