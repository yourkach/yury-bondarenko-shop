package com.example.yury_bondarenko_shop.presenter

import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.BasketItemsDao
import com.example.yury_bondarenko_shop.domain.CheckoutPriceFormatter
import com.example.yury_bondarenko_shop.domain.MainApi
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.domain.model.OrderModel
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteOrder
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.InjectViewState
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

@InjectViewState
class CheckoutPresenter @Inject constructor(
    private val mainApi: MainApi,
    private val basketItemsDao: BasketItemsDao,
    private val checkoutPriceFormatter: CheckoutPriceFormatter
) : BasePresenter<CheckoutView>() {

    private lateinit var basketItems: List<BasketItem>

    private val model = OrderModel()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setOrderInfo()
    }

    private fun setOrderInfo() {
        basketItems = basketItemsDao.getAllItems()
        viewState.setBasketItemsCount(basketItems.sumBy { it.count })
        viewState.setTotalPrice(checkoutPriceFormatter.formatPrice(calcPurchaseDiscountSum()))
        viewState.setRawPrice(checkoutPriceFormatter.formatPrice(calcPurchaseRawPricesSum()))
        viewState.setDiscount(checkoutPriceFormatter.formatPrice(calcTotalDiscount()))
    }

    fun onFirstNameChanged(text: String) {
        model.firstName = text
        viewState.showErrorFirstName(!lengthIsCorrect(text))
    }

    fun onLastNameChanged(text: String) {
        model.lastName = text
        viewState.showErrorLastName(!lengthIsCorrect(text))
    }

    fun onPhoneNumberChanged(text: String) {
        model.phoneNumber = text
        viewState.showErrorPhone(!numberIsCorrect(text))
    }

    fun onPaymentMethodChanged(newPaymentType: RemoteOrder.PaymentType) {
        model.paymentType = newPaymentType
    }

    fun onMakeOrderClick() {
        when {
            basketItems.isEmpty() -> {
                viewState.showMessage(R.string.err_empty_order)
            }
            orderModelIsCorrect() -> {
                val remoteOrder =
                    RemoteOrder(
                        model.firstName,
                        model.lastName,
                        model.phoneNumber,
                        model.paymentType,
                        basketItems.map { RemoteOrder.Item(it.product.id, it.count) }
                    )
                launch {
                    mainApi.createOrder(remoteOrder)
                    viewState.showMessage(R.string.msg_order_processed)
                    basketItemsDao.clearBasket()
                    viewState.closeView()
                }
            }
            else -> {
                viewState.showMessage(R.string.err_incorrect_fields_value)
            }
        }
    }

    private fun orderModelIsCorrect(): Boolean {
        return lengthIsCorrect(model.firstName)
                && lengthIsCorrect(model.lastName)
                && numberIsCorrect(model.phoneNumber)
    }

    private fun lengthIsCorrect(text: String): Boolean = text.length > 1

    private fun numberIsCorrect(text: String): Boolean {
        return text.matches(Regex("((\\+7|8)([0-9]){10})"))
    }

    private fun calcPurchaseDiscountSum(): Double =
        basketItems.sumByDouble { it.product.discountPrice * it.count }

    private fun calcPurchaseRawPricesSum(): Double =
        basketItems.sumByDouble { it.product.price * it.count }

    private fun calcTotalDiscount(): Double = calcPurchaseRawPricesSum() - calcPurchaseDiscountSum()

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
    }
}

