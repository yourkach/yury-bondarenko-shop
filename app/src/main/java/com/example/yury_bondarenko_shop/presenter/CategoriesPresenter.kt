package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject


@InjectViewState
class CategoriesPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val categoriesList: List<RemoteCategory>
) : BasePresenter<CategoriesView>() {

    private val categories = mutableListOf<RemoteCategory>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (categoriesList.sumBy { it.products.size } > 0) {
            val allProducts = mutableListOf<RemoteProduct>()
            categoriesList.forEach {
                categories.add(it)
                allProducts.addAll(it.products)
            }
            categories.add(RemoteCategory("Все товары", allProducts))
        }
        viewState.setCategoriesList(categories.map { it.name })

    }

    override fun attachView(view: CategoriesView?) {
        super.attachView(view)
        updateBasketItemsCount()
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
}

class CategoriesPresenterFactory @Inject constructor(
    private val basketItemsDao: BasketItemsDao
) {
    fun create(categoriesList: List<RemoteCategory>): CategoriesPresenter {
        return CategoriesPresenter(basketItemsDao, categoriesList)
    }
}