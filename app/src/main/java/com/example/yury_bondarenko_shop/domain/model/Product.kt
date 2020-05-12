package com.example.yury_bondarenko_shop.domain.model

import android.os.Parcelable
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Product(
    val id: String,
    val price: Double,
    val salePercent: Int = 0,
    val productName: String,
    val imageUrl: String,
    val description: String,
    val attributes: List<String>
) : Parcelable {
    /**
     * price with applied discount determined by [salePercent]
     * If [salePercent] is more than 100 then product will have negative price
     * If [salePercent] less than 0 product price will be increased
     */
    val discountPrice: Double
        get() = price * (1 - salePercent / 100.0)
}


class ProductFactory {
    fun createProduct(remote: RemoteProduct): Product =
        Product(
            remote.id,
            remote.price,
            remote.discountPercent,
            remote.name,
            remote.imageUrl,
            remote.description,
            remote.attributes.map { it.name + ": " + it.value }
        )
}