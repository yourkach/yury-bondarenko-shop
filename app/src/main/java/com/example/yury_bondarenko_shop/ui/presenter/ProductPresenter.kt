package com.example.yury_bondarenko_shop.ui.presenter

import com.example.yury_bondarenko_shop.data.Product
import com.example.yury_bondarenko_shop.view.ProductView
import com.example.yury_bondarenko_shop.model.CreateOrderModel
import moxy.MvpPresenter

class ProductPresenter : MvpPresenter<ProductView>() {
    private val itemsList: List<Product> = listOf()

    private val model = CreateOrderModel()

    fun checkFirstName(text: String) {
        if (lengthIsCorrect(text)) model.firstName = text
        viewState.showErrorFirstName(!lengthIsCorrect(text))
    }

    fun checkLastName(text: String) {
        if (lengthIsCorrect(text)) model.lastName = text
        viewState.showErrorLastName(!lengthIsCorrect(text))
    }

    fun checkMiddleName(text: String) {
        if (lengthIsCorrect(text)) model.middleName = text
        viewState.showErrorMiddleName(!lengthIsCorrect(text))
    }

    fun checkPhoneNumber(text: String) {
        val isCorrect = numberIsCorrect(text)
        if (isCorrect) model.phoneNumber = text
        viewState.showErrorPhone(!isCorrect)
    }

    private fun lengthIsCorrect(text: String): Boolean = text.length > 2

    private fun numberIsCorrect(text: String): Boolean {
        return text.matches(Regex("((\\+7|8)([0-9]){10})"))
    }

    fun printBasket() {
        itemsList.forEachIndexed { index, it ->
            viewState.print("$index. ${it.getProductName()}: ${it.calcDiscountPrice()}")
        }
        viewState.print("Total: ${calcPurchaseSum()}")
    }

    private fun calcPurchaseSum(): Double = itemsList.sumByDouble { it.calcDiscountPrice() }
}

