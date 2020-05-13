package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.MainApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class SplashPresenter @Inject constructor(
    private val mainApi: MainApi
) : BasePresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launch {
            val categories =
                async { mainApi.allProductsWithCategories() }
            delay(2000)
            viewState.startCategoriesActivity(categories.await())
        }
    }

    override fun onFailure(e: Throwable) {
        super.onFailure(e)
        when (e) {
            is ConnectException -> {
                viewState.showMessage(R.string.err_connection_error)
            }
            is UnknownHostException -> {
                viewState.showMessage(R.string.err_connection_error)
            }
        }
        viewState.startCategoriesActivity(listOf())
    }
}