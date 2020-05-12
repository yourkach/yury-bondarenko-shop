package com.example.yury_bondarenko_shop.domain.model.remote

import android.os.Parcelable
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RemoteCategory(
    val name: String,
    val products: List<RemoteProduct>
) : Parcelable