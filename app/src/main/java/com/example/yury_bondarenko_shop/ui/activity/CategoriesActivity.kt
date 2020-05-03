package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.presenter.CategoriesPresenter
import com.example.yury_bondarenko_shop.presenter.CategoriesView
import com.example.yury_bondarenko_shop.ui.adapter.CategoriesAdapter
import kotlinx.android.synthetic.main.activity_categories.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class CategoriesActivity : MvpAppCompatActivity(), CategoriesView {

    @Inject
    lateinit var categoriesPresenter: CategoriesPresenter

    private val presenter by moxyPresenter { categoriesPresenter }

    private val categoriesAdapter =
        CategoriesAdapter(onCategoryClick = { pos -> presenter.onCategoryClick(pos) })

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setListeners()
        setUpCategoriesRecycler()
    }

    private fun setUpCategoriesRecycler() {
        categoriesRv.layoutManager = GridLayoutManager(this, 2)
        categoriesRv.adapter = categoriesAdapter
    }

    private fun setListeners() {
        categoriesOpenCartIv.setOnClickListener {
            presenter.onBasketClick()
        }
    }

    override fun setCategoriesList(categories: List<String>) {
        categoriesAdapter.setList(categories)
    }

    override fun startCatalogActivity(category: String) {
        val intent = Intent(this, CatalogActivity::class.java)
        intent.putExtra("title", category)
        startActivity(intent)
    }

    override fun startBasketActivity() {
        startActivity(Intent(this, BasketActivity::class.java))
    }

}
