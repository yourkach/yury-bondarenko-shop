package com.example.yury_bondarenko_shop

class BasketPresenter(
    private val itemsList: List<Product>,
    private val productView: ProductView
) {

    fun printBasket() {
        itemsList.forEachIndexed { index, it ->
            productView.print("$index. ${it.getProductName()}: ${it.calcDiscountPrice()}")
        }
        productView.print("Total: ${calcPurchaseSum()}")
    }

    private fun calcPurchaseSum(): Double = itemsList.sumByDouble { it.calcDiscountPrice() }
}