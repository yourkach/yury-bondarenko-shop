package com.example.yury_bondarenko_shop.domain

import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteProduct
import retrofit2.http.GET
import retrofit2.http.Path


interface MainApi {

    @GET("products/all/{author}")
    suspend fun allProducts(@Path("author") author: String): List<RemoteProduct>

    @GET("products/allWithCategories/{author}/")
    suspend fun allProductsWithCategories(@Path("author") author: String): List<RemoteCategory>
}