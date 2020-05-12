package com.example.yury_bondarenko_shop.ui.adapter.basket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.bumptech.glide.Glide
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.BasketItem
import com.example.yury_bondarenko_shop.getStrikethroughSpannable
import kotlinx.android.synthetic.main.item_basket_layout.view.*

class BasketAdapter(
    val callbackDelegate: BasketItemClickCallback,
    val formatPrice: (price: Double) -> String
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var items: List<BasketItem> = listOf<BasketItem>()

    fun setList(basketItems: List<BasketItem>) {
        val diffResult = DiffUtil.calculateDiff(
            BasketItemDiffUtilCallback(
                items,
                basketItems
            )
        )
        items = basketItems
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

    //TODO refactor
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: BasketItem) {
            val product = item.product
            itemView.apply {
                Glide.with(basketItemPicIv.context)
                    .load(product.imageUrl)
                    .error(R.drawable.ic_catalog_item_stub)
                    .into(basketItemPicIv)
                basketItemName.text = product.productName
                basketItemCountTv.text = item.count.toString()
                if (product.salePercent != 0) {
                    basketItemDiscountPrice.visibility = View.VISIBLE
                    basketItemDiscountPrice.text = formatPrice(product.discountPrice)
                    val rawPrice = formatPrice(product.price)
                    basketItemMainPrice.text = getStrikethroughSpannable(rawPrice)
                } else {
                    basketItemMainPrice.text = formatPrice(product.price)
                    basketItemDiscountPrice.visibility = View.GONE
                }
            }
            setListeners()
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
                        callbackDelegate.onCountMinusClick(adapterPosition)
                    }
                }
                basketItemCountPlusIv.setOnClickListener {
                    if (adapterPosition != NO_POSITION) {
                        callbackDelegate.onCountPlusClick(adapterPosition)
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