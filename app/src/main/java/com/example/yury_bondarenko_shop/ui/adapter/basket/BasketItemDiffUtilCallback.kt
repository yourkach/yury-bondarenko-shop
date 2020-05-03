package com.example.yury_bondarenko_shop.ui.adapter.basket

import androidx.recyclerview.widget.DiffUtil
import com.example.yury_bondarenko_shop.domain.model.BasketItem


class BasketItemDiffUtilCallback(
    private val oldList: List<BasketItem>,
    private val newList: List<BasketItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem: BasketItem = oldList[oldItemPosition]
        val newItem: BasketItem = newList[newItemPosition]
        return oldItem.product.id == newItem.product.id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldItem: BasketItem = oldList[oldItemPosition]
        val newItem: BasketItem = newList[newItemPosition]
        return oldItem.count == newItem.count && oldItem.product == newItem.product
    }

}