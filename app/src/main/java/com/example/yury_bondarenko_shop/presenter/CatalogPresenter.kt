package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.CommonPriceFormatter
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.ProductFactory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class CatalogPresenter(
    private val basketItemsDao: BasketItemsDao,
    private val commonPriceFormatter: CommonPriceFormatter,
    private val mainApi: MainApi,
    private val category: RemoteCategory
) : BasePresenter<CatalogView>() {

    private lateinit var allProducts: List<Product>

    private var searchResults: List<Product> = listOf()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        val factory = ProductFactory()
        allProducts = category.products.map { factory.createProduct(it) }
        viewState.setProductList(allProducts)
        viewState.setCategoryTitle(category.name)
    }

    override fun attachView(view: CatalogView?) {
        super.attachView(view)
        updateBasketItemsCount() //TODO move to onViewResume
    }


    //leave for stepik homework
    private fun doNetworkRequest() {
        launch {
            val products = mainApi.allProducts(author = "default")
        }
    }


    fun onQueryChanged(query: String?) {
        if (query.isNullOrEmpty()) {
            viewState.setProductList(allProducts)
        } else {
            searchResults =
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

    fun onQueryChanged() {

    }

    fun formatPrice(price: Double): String {
        return commonPriceFormatter.formatPrice(price)
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        when (e) {
            is ConnectException -> {
                viewState.showMessage(R.string.err_no_internet_connection)
            }
            is UnknownHostException -> {
                viewState.showMessage(R.string.err_connection_error)
            }
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
    private val basketItemsDao: BasketItemsDao,
    private val commonPriceFormatter: CommonPriceFormatter,
    private val mainApi: MainApi
) {
    fun create(category: RemoteCategory): CatalogPresenter {
        return CatalogPresenter(basketItemsDao, commonPriceFormatter, mainApi, category)
    }
}