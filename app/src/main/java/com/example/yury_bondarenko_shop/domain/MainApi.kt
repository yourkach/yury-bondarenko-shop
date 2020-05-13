package com.example.yury_bondarenko_shop.domain

import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteOrder
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface MainApi {

    @GET("products/all/{author}")
    suspend fun allProducts(@Path("author") author: String): List<RemoteProduct>

    @GET("products/allWithCategories/Bondarenko/")
    suspend fun allProductsWithCategories(): List<RemoteCategory>

    @POST("orders/new/Bondarenko/")
    suspend fun createOrder(@Body order: RemoteOrder)
}