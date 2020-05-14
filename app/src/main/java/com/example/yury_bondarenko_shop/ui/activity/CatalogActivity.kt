package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.presenter.CatalogPresenterFactory
import com.example.yury_bondarenko_shop.presenter.CatalogView
import com.example.yury_bondarenko_shop.ui.activity.DetailedActivity.Companion.DETAILED_LAUNCHED_FROM
import com.example.yury_bondarenko_shop.ui.activity.DetailedActivity.Companion.DETAILED_PRODUCT_KEY
import com.example.yury_bondarenko_shop.ui.adapter.CatalogAdapter
import kotlinx.android.synthetic.main.activity_catalog.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CatalogActivity : MvpAppCompatActivity(), CatalogView {


    @Inject
    lateinit var catalogPresenterFactory: CatalogPresenterFactory

    private val presenter by moxyPresenter { catalogPresenterFactory.create(category) }

    private lateinit var category: RemoteCategory

    private val catalogAdapter = CatalogAdapter(
        onItemClick = { product -> presenter.onItemClicked(product) },
        onAddToBasketClick = { product -> presenter.addProductToBasket(product) },
        formatPrice = { price -> presenter.formatPrice(price) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        category = intent.getParcelableExtra(CATEGORY_TAG)!!
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
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
        catalogSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.onQueryChanged(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.onQueryChanged(newText)
                return true
            }
        })
    }

    private fun setUpProductsRecycler() {
        catalogProductsRv.apply {
            layoutManager = GridLayoutManager(this@CatalogActivity, 2)
            adapter = catalogAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }

    override fun startDetailedActivity(product: Product) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra(DETAILED_PRODUCT_KEY, product)
        intent.putExtra(DETAILED_LAUNCHED_FROM, this::class.java.simpleName)
        startActivity(intent)
    }

    override fun startBasketActivity() {
        startActivity(Intent(this, BasketActivity::class.java))
    }


    override fun setProductList(products: List<Product>) {
        catalogAdapter.setList(products)
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }

    override fun setBasketCountLabelVisibility(visible: Boolean) {
        catalogBasketItemsCountTv.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setBasketCountText(text: String) {
        catalogBasketItemsCountTv.text = text
    }

    override fun setCategoryTitle(title: String) {
        catalogHeaderLabel.text = title
    }

    companion object {
        const val CATEGORY_TAG = "CATEGORY_TAG"
    }
}
