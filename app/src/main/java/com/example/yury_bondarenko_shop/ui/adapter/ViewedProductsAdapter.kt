package com.example.yury_bondarenko_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.getStrikethroughSpannable
import kotlinx.android.synthetic.main.item_viewed_product_layout.view.*

class ViewedProductsAdapter(private val formatPrice: (price: Double) -> String) :
    RecyclerView.Adapter<ViewedProductsAdapter.ViewHolder>() {

    private var items: List<Product> = listOf()

    fun setList(newItems: List<Product>) {
        items = newItems
        DiffUtil.calculateDiff(ProductDiffUtilCallback(items, newItems)).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_viewed_product_layout, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.apply {
                viewedName.text = product.productName
                if (product.salePercent == 0) {
                    viewedDiscountPrice.visibility = View.GONE
                    viewedMainPrice.text = formatPrice(product.price)
                    viewedDiscountPrice.text = ""
                } else {
                    viewedDiscountPrice.visibility = View.VISIBLE
                    viewedMainPrice.text = getStrikethroughSpannable(formatPrice(product.price))
                    viewedDiscountPrice.text = formatPrice(product.discountPrice)
                }
            }
        }
    }
}