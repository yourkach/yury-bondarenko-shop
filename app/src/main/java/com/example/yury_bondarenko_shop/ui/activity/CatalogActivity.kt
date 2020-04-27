package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.data.ViewedProductDaoImpl
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.presenter.CatalogPresenter
import com.example.yury_bondarenko_shop.presenter.CatalogView
import com.example.yury_bondarenko_shop.sharedPreferences
import com.example.yury_bondarenko_shop.ui.adapter.ViewedProductsAdapter
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class CatalogActivity : MvpAppCompatActivity(), CatalogView {

    private val presenter by moxyPresenter {
        CatalogPresenter(
            ViewedProductDaoImpl(sharedPreferences)
        )
    }

    private val adapter = ViewedProductsAdapter {
        presenter.formatPrice(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        setListeners()
        setUpViewedRecycler()
    }

    private fun setUpViewedRecycler() {
        catalogViewedProductsRv.apply {
            layoutManager =
                LinearLayoutManager(this@CatalogActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@CatalogActivity.adapter
        }
    }

    private fun setListeners() {
        catalogShowBasketBtn.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }
    }

    override fun setViewedItemsList(newItems: List<Product>) {
        adapter.setList(newItems)
    }

}
