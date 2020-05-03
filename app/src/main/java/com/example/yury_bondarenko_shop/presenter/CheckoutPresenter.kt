package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.domain.model.CreateOrderModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CheckoutPresenter : MvpPresenter<CheckoutView>() {
    private val itemsList: List<Product> = listOf()

    private val model =
        CreateOrderModel()

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

    private fun calcPurchaseSum(): Double = itemsList.sumByDouble { it.discountPrice }
}

