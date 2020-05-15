package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.ViewedProductsDao
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CategoriesPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val viewedProductsDao: ViewedProductsDao,
    private val categoriesList: List<RemoteCategory>
) : BasePresenter<CategoriesView>() {

    private val categories = mutableListOf<RemoteCategory>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (categoriesList.sumBy { it.products.size } > 0) {
            val allProducts = mutableListOf<RemoteProduct>()
            val discountProducts = mutableListOf<RemoteProduct>()
            categoriesList.forEach { category ->
                categories.add(category)
                allProducts.addAll(category.products)
                category.products.forEach { product ->
                    if (product.discountPercent > 0) discountProducts.add(product)
                }
            }
            categories.add(RemoteCategory("Все товары", allProducts))
            categories.add(RemoteCategory("Товары со скидкой %", discountProducts))
        }
        viewState.setCategoriesList(categories.map { it.name })
    }

    private fun updateViewedItems() {
        val viewedItems = viewedProductsDao.getAllProducts()
        viewState.setViewedProductsVisibility(viewedItems.isNotEmpty())
        if (viewedItems.isNotEmpty()) {
            viewState.setViewedItems(viewedItems)
        }
    }


    fun onViewResume() {
        updateBasketItemsCount()
        updateViewedItems()
    }

    private fun updateBasketItemsCount() {
        val itemsCount = basketItemsDao.getItemsCount()
        if (itemsCount > 0) {
            val formattedCount = if (itemsCount < 10) itemsCount.toString() else "9+"
            viewState.setBasketCountLabelVisibility(true)
            viewState.setBasketCountText(formattedCount)
        } else {
            viewState.setBasketCountLabelVisibility(false)
        }
    }

    fun onCategoryClick(pos: Int) {
        viewState.startCatalogActivity(categories[pos])
    }

    fun onBasketClick() {
        viewState.startBasketActivity()
    }

    fun onViewedItemClick(product: Product) {
        viewState.startDetailedActivity(product)
    }
}

class CategoriesPresenterFactory @Inject constructor(
    private val basketItemsDao: BasketItemsDao,
    private val viewedProductsDao: ViewedProductsDao
) {
    fun create(categoriesList: List<RemoteCategory>): CategoriesPresenter {
        return CategoriesPresenter(basketItemsDao, viewedProductsDao, categoriesList)
    }
}