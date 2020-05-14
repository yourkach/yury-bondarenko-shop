package com.example.yury_bondarenko_shop.domain.model

import com.example.yury_bondarenko_shop.domain.model.remote.RemoteOrder

/**
 * model for creating order
 */
data class OrderModel(
    var firstName: String = "",
    var lastName: String = "",
    var phoneNumber: String = "",
    var paymentType: RemoteOrder.PaymentType = RemoteOrder.PaymentType.CashOnReceiving
)