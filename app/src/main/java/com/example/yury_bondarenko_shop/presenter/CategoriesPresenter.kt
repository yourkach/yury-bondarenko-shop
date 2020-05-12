package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class CategoriesPresenter @Inject constructor(
    private val basketItemsDao: BasketItemsDao,
    private val mainApi: MainApi
) : BasePresenter<CategoriesView>() {

    private var categoriesList = listOf<RemoteCategory>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            //TODO("change author")
            categoriesList = mainApi.allProductsWithCategories(author = "Kolyvanov")
            viewState.setCategoriesList(categoriesList.map { it.name })
        }
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
        viewState.startCatalogActivity(categoriesList[pos])
    }

    fun onBasketClick() {
        viewState.startBasketActivity()
    }

}
