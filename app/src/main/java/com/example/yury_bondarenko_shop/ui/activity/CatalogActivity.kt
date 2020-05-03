package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.presenter.CatalogPresenter
import com.example.yury_bondarenko_shop.presenter.CatalogView
import com.example.yury_bondarenko_shop.ui.adapter.CatalogAdapter
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogActivity : MvpAppCompatActivity(), CatalogView {


    @Inject
    lateinit var catalogPresenter: CatalogPresenter

    private val presenter by moxyPresenter { catalogPresenter }

    private val catalogAdapter = CatalogAdapter(
        onItemClick = { product -> presenter.onItemClicked(product) },
        formatPrice = { price -> presenter.formatPrice(price) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        /*
        val title = intent.extras?.getString("title")!!
        catalogHeaderLabel.text = title
        */
        setListeners()
        setUpProductsRecycler()
    }

    private fun setListeners() {
        catalogBackIv.setOnClickListener {
            finish()
        }
        catalogOpenBasketIv.setOnClickListener {
            presenter.onBasketClicked()
        }
    }

    private fun setUpProductsRecycler() {
        catalogProductsRv.apply {
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            adapter = catalogAdapter
        }
    }

    override fun startDetailedActivity(product: Product) {
        //TODO("pass product to detailed activity")
        startActivity(Intent(this, DetailedActivity::class.java))
    }

    override fun startBasketActivity() {
        startActivity(Intent(this, BasketActivity::class.java))
    }


    override fun setProductList(products: List<Product>) {
        catalogAdapter.setList(products)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
