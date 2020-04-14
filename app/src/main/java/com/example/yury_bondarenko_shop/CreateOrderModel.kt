package com.example.yury_bondarenko_shop

/**
 * Модель для создания заказа
 */
data class CreateOrderModel(
    var firstName: String = "",
    var lastName: String = "",
    var middleName: String = "",
    var phoneNumber: String = ""
)