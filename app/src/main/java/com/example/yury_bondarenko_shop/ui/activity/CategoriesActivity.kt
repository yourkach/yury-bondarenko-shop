package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.presenter.CategoriesPresenter
import com.example.yury_bondarenko_shop.presenter.CategoriesPresenterFactory
import com.example.yury_bondarenko_shop.presenter.CategoriesView
import com.example.yury_bondarenko_shop.ui.activity.CatalogActivity.Companion.CATEGORY_TAG
import com.example.yury_bondarenko_shop.ui.adapter.CategoriesAdapter
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

    private lateinit var categoriesList: List<RemoteCategory>

    override fun onCreate(savedInstanceState: Bundle?) {
        categoriesList = intent.getParcelableArrayListExtra(CATEGORIES_LIST_TAG)!!
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setListeners()
        setUpCategoriesRecycler()
    }

    private fun setUpCategoriesRecycler() {
        categoriesRv.layoutManager = LinearLayoutManager(this)
        categoriesRv.adapter = categoriesAdapter
        categoriesRv.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setListeners() {
        categoriesOpenBasketIv.setOnClickListener {
            presenter.onBasketClick()
        }
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(this, getString(stringResId), Toast.LENGTH_SHORT).show()
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
