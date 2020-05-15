package com.example.yury_bondarenko_shop.ui.adapter.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.Glide
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.commonPriceFormat
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.strikethroughSpannable
import kotlinx.android.synthetic.main.item_basket_layout.view.*

class BasketAdapter(
    val callbackDelegate: BasketItemClickCallback
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var items: List<BasketItem> = listOf<BasketItem>()

    fun setList(basketItems: List<BasketItem>) {
        val diffResult = DiffUtil.calculateDiff(
            BasketItemDiffUtilCallback(
                items,
                basketItems
            )
        )
        items = basketItems.toList()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_basket_layout, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(basketItem: BasketItem) {
            loadProductImage(basketItem.product.imageUrl)
            setProductInfo(basketItem)
            setListeners()
        }

        private fun setProductInfo(basketItem: BasketItem) {
            val product = basketItem.product
            itemView.apply {
                basketItemName.text = product.productName
                basketItemCountTv.text = basketItem.count.toString()
                if (product.salePercent != 0) {
                    basketItemDiscountPrice.visibility = View.VISIBLE
                    basketItemDiscountPrice.text = product.discountPrice.commonPriceFormat
                    basketItemMainPrice.text =
                        product.price.commonPriceFormat.strikethroughSpannable
                } else {
                    basketItemMainPrice.text = product.price.commonPriceFormat
                    basketItemDiscountPrice.visibility = View.GONE
                }
            }
        }

        private fun loadProductImage(imageUrl: String) {
            Glide.with(itemView.basketItemPicIv.context)
                .load(imageUrl)
                .error(R.drawable.ic_catalog_item_stub)
                .into(itemView.basketItemPicIv)
        }

        private fun setListeners() {
            itemView.apply {
                basketItemDeleteLl.setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        callbackDelegate.onDeleteClick(adapterPosition)
                    }
                }
                basketItemCountMinusIv.setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        val newCount = callbackDelegate.onCountMinusClick(adapterPosition)
                        basketItemCountTv.text = newCount.toString()
                    }
                }
                basketItemCountPlusIv.setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        val newCount = callbackDelegate.onCountPlusClick(adapterPosition)
                        basketItemCountTv.text = newCount.toString()
                    }
                }
                itemRootCl.setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        callbackDelegate.onItemClick(adapterPosition)
                    }
                }
            }
        }

    }


}