package com.example.yury_bondarenko_shop.domain.interactor

import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.ViewedProductDao
import javax.inject.Inject

class AddProductToCartUseCase @Inject constructor(
    private val mainApi: MainApi,
    private val viewedProductDao: ViewedProductDao
) {

    operator fun invoke() {

    }

}