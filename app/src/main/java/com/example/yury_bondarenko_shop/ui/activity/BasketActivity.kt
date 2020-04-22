package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.data.Product
import com.example.yury_bondarenko_shop.ui.adapter.BasketAdapter
import com.example.yury_bondarenko_shop.ui.presenter.BasketPresenter
import com.example.yury_bondarenko_shop.view.BasketView
import kotlinx.android.synthetic.main.activity_basket.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class BasketActivity : MvpAppCompatActivity(), BasketView {

    @InjectPresenter
    lateinit var presenter: BasketPresenter

    private var adapter: BasketAdapter = BasketAdapter(
        onDeleteClick = { position ->
            presenter.onBasketItemDeleteClick(position)
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
            adapter = this@BasketActivity.adapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }


    private fun setListeners() {
        basketBackIv.setOnClickListener {
            finish()
        }
        basketCheckoutBtn.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }
        basketAddItemBtn.setOnClickListener {
            presenter.onAddItemClick()
        }
    }

    override fun setBasketItems(items: List<Product>) {
        adapter.submitList(items)
    }

    override fun updateBasketItems() {
        adapter.notifyDataSetChanged()
    }

    override fun removeItem(pos: Int) {
        adapter.notifyItemRemoved(pos)
    }

    override fun addItem(pos: Int) {
        adapter.notifyItemInserted(pos)
    }

    override fun smoothScrollToPosition(pos: Int) {
        basketItemsRv.smoothScrollToPosition(pos)
    }
}
