package com.example.yury_bondarenko_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.getStrikethroughSpannable
import kotlinx.android.synthetic.main.item_basket_layout.view.*

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
                itemName.text = product.productName
                if (product.salePercent != 0) {
                    itemDiscountPrice.visibility = View.VISIBLE
                    itemDiscountPrice.text = formatPrice(product.discountPrice)
                    val rawPrice = formatPrice(product.price)
                    itemMainPrice.text = getStrikethroughSpannable(rawPrice)
                } else {
                    itemMainPrice.text = formatPrice(product.price)
                    itemDiscountPrice.visibility = View.GONE
                }
                itemDeleteIv.setOnClickListener {
                    if (adapterPosition != NO_POSITION) onDeleteClick(adapterPosition)
                }
            }
        }

    }


}