package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.ui.adapter.basket.BasketAdapter
import com.example.yury_bondarenko_shop.presenter.BasketPresenter
import com.example.yury_bondarenko_shop.presenter.BasketView
import com.example.yury_bondarenko_shop.ui.activity.DetailedActivity.Companion.DETAILED_LAUNCHED_FROM
import com.example.yury_bondarenko_shop.ui.activity.DetailedActivity.Companion.DETAILED_PRODUCT_KEY
import com.example.yury_bondarenko_shop.ui.adapter.basket.BasketItemClickCallback
import com.example.yury_bondarenko_shop.ui.adapter.basket.BasketSwipeCallback
import kotlinx.android.synthetic.main.activity_basket.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class BasketActivity : MvpAppCompatActivity(),
    BasketView {

    @Inject
    lateinit var basketPresenter: BasketPresenter

    private val presenter: BasketPresenter by moxyPresenter {
        basketPresenter
    }

    private val basketAdapter: BasketAdapter =
        BasketAdapter(
            object : BasketItemClickCallback {
                override fun onDeleteClick(pos: Int) {
                    presenter.onBasketItemDeleteClick(pos)
                }

                override fun onCountPlusClick(pos: Int): Int = presenter.onItemCountPlusClick(pos)

                override fun onCountMinusClick(pos: Int): Int = presenter.onItemCountMinusClick(pos)

                override fun onItemClick(pos: Int) {
                    presenter.onItemClick(pos)
                }
            }
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
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
        ItemTouchHelper(
            BasketSwipeCallback(
                onItemSwiped = { pos ->
                    presenter.onBasketItemDeleteClick(pos)
                },
                adapter = basketAdapter,
                icon = getDrawable(R.drawable.ic_delete)!!,
                background = getDrawable(R.drawable.bg_delete_rounded_red)!!
            )
        ).also { it.attachToRecyclerView(basketItemsRv) }
    }


    private fun setListeners() {
        basketBackIv.setOnClickListener {
            finish()
        }
        basketCheckoutBtn.setOnClickListener {
            presenter.onCheckoutClick()
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.onViewPause()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }

    override fun startCheckoutActivity() {
        startActivity(Intent(this, CheckoutActivity::class.java))
    }

    override fun startDetailedActivity(product: Product) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra(DETAILED_PRODUCT_KEY, product)
        intent.putExtra(DETAILED_LAUNCHED_FROM, this::class.java.simpleName)
        startActivity(intent)
    }

    override fun setCheckoutButtonEnabled(enabled: Boolean) {
        basketCheckoutBtn.isEnabled = enabled
        if (enabled) {
            basketCheckoutBtn.setBackgroundResource(R.drawable.bg_button_rounded_black)
        } else {
            basketCheckoutBtn.setBackgroundResource(R.drawable.bg_button_disabled)
        }
    }

    override fun setEmptyMsgVisibility(visible: Boolean) {
        basketEmptyMsgTv.visibility = if (visible) View.VISIBLE else View.GONE
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
