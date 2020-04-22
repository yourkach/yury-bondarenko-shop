package com.example.yury_bondarenko_shop.ui.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.data.Product
import kotlinx.android.synthetic.main.item_basket_layout.view.*
import kotlinx.android.synthetic.main.item_category_layout.view.*

class BasketAdapter(
    val onDeleteClick: (pos: Int) -> Unit,
    val formatPrice: (price: Double) -> String
) : RecyclerView.Adapter<BasketAdapter.ViewHolder>() {

    private var items: List<Product> = listOf<Product>()

    fun submitList(basketItems: List<Product>) {
        items = basketItems
        notifyDataSetChanged()
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
        fun bind(product: Product) {
            itemView.apply {
                itemName.text = product.getProductName()
                if (product.getSalePercent() != 0) {
                    itemDiscountPrice.visibility = View.VISIBLE
                    itemDiscountPrice.text = formatPrice(product.calcDiscountPrice())
                    val rawPrice = formatPrice(product.getRawPrice())
                    itemMainPrice.text = getStrikethroughText(rawPrice)
                } else {
                    itemMainPrice.text = formatPrice(product.getRawPrice())
                    itemDiscountPrice.visibility = View.GONE
                }
                itemDeleteIv.setOnClickListener {
                    if (adapterPosition != NO_POSITION) onDeleteClick(adapterPosition)
                }
            }
        }

        private fun getStrikethroughText(text: String): SpannableString {
            val spannable = SpannableString(text)
            spannable.setSpan(StrikethroughSpan(), 0, text.length, 0)
            return spannable
        }

    }


}