package com.example.yury_bondarenko_shop.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.yury_bondarenko_shop.domain.model.Product


class ProductDiffUtilCallback(
    private val oldList: List<Product>,
    private val newList: List<Product>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldProduct: Product = oldList[oldItemPosition]
        val newProduct: Product = newList[newItemPosition]
        return oldProduct.productName === newProduct.productName
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldProduct: Product = oldList[oldItemPosition]
        val newProduct: Product = newList[newItemPosition]
        return oldProduct == newProduct
    }

}