package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.ProductFactory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class CatalogPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val category: RemoteCategory
) : BasePresenter<CatalogView>() {

    private lateinit var allProducts: List<Product>

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val factory = ProductFactory()
        allProducts = category.products.map { factory.createProduct(it) }
        viewState.setProductList(allProducts)
        viewState.setCategoryTitle(category.name)
    }

    fun onViewResume() {
        updateBasketItemsCount()
    }

    fun onQueryChanged(query: String?) {
        if (query.isNullOrEmpty()) {
            viewState.setProductList(allProducts)
        } else {
            val searchResults =
                allProducts.mapNotNull { if (it.productName.contains(query, true)) it else null }
            viewState.setProductList(searchResults)
        }
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

    fun onBasketClicked() {
        viewState.startBasketActivity()
    }

    fun addProductToBasket(product: Product) {
        basketItemsDao.addProduct(product)
        updateBasketItemsCount()
    }

    fun onItemClicked(product: Product) {
        viewState.startDetailedActivity(product)
    }
}


class CatalogPresenterFactory @Inject constructor(
    private val basketItemsDao: BasketItemsDao
) {
    fun create(category: RemoteCategory): CatalogPresenter {
        return CatalogPresenter(basketItemsDao, category)
    }
}