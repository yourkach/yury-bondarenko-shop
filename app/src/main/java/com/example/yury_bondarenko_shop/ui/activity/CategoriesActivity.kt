package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.presenter.CategoriesPresenter
import com.example.yury_bondarenko_shop.presenter.CategoriesPresenterFactory
import com.example.yury_bondarenko_shop.presenter.CategoriesView
import com.example.yury_bondarenko_shop.ui.activity.CatalogActivity.Companion.CATEGORY_TAG
import com.example.yury_bondarenko_shop.ui.adapter.CategoriesAdapter
import com.example.yury_bondarenko_shop.ui.adapter.ViewedProductsAdapter
import kotlinx.android.synthetic.main.activity_categories.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CategoriesActivity : MvpAppCompatActivity(), CategoriesView {

    @Inject
    lateinit var categoriesPresenter: CategoriesPresenterFactory

    private val presenter by moxyPresenter { categoriesPresenter.create(categoriesList) }

    private val categoriesAdapter =
        CategoriesAdapter(onCategoryClick = { pos -> presenter.onCategoryClick(pos) })

    private val viewedProductsAdapter =
        ViewedProductsAdapter(onItemClick = { presenter.onViewedItemClick(it) })

    private lateinit var categoriesList: List<RemoteCategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        categoriesList = intent.getParcelableArrayListExtra(CATEGORIES_LIST_TAG)!!
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setListeners()
        setUpCategoriesRecycler()
        setUpViewedRecycler()
    }

    private fun setUpViewedRecycler() {
        categoriesViewedProductsRv.apply {
            layoutManager =
                LinearLayoutManager(
                    this@CategoriesActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = viewedProductsAdapter
        }
    }

    private fun setUpCategoriesRecycler() {
        categoriesRv.layoutManager = LinearLayoutManager(this)
        categoriesRv.adapter = categoriesAdapter
    }

    private fun setListeners() {
        categoriesOpenBasketIv.setOnClickListener {
            presenter.onBasketClick()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }

    override fun setViewedItems(newItems: List<Product>) {
        viewedProductsAdapter.setList(newItems)
    }

    override fun setViewedProductsVisibility(visible: Boolean) {
        categoriesViewedProductsCl.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun startDetailedActivity(product: Product) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra(DetailedActivity.DETAILED_PRODUCT_KEY, product)
        intent.putExtra(DetailedActivity.DETAILED_LAUNCHED_FROM, this::class.java.simpleName)
        startActivity(intent)
    }

    override fun setCategoriesList(categories: List<String>) {
        categoriesAdapter.setList(categories)
    }

    override fun startCatalogActivity(category: RemoteCategory) {
        val intent = Intent(this, CatalogActivity::class.java)
        intent.putExtra(CATEGORY_TAG, category)
        startActivity(intent)
    }

    override fun startBasketActivity() {
        startActivity(Intent(this, BasketActivity::class.java))
    }

    override fun setBasketCountLabelVisibility(visible: Boolean) {
        categoriesBasketItemsCountTv.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setBasketCountText(text: String) {
        categoriesBasketItemsCountTv.text = text
    }

    companion object {
        const val CATEGORIES_LIST_TAG = "CATEGORIES_TAG"
    }
}
