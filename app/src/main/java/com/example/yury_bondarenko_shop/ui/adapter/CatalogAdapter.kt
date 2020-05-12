package com.example.yury_bondarenko_shop.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.getStrikethroughSpannable
import kotlinx.android.synthetic.main.item_catalog_layout.view.*
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemDescr
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemMainPrice
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemName
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemPicIv
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemRawPrice
import kotlinx.android.synthetic.main.item_catalog_layout.view.catalogItemRootCl

class CatalogAdapter(
    val onItemClick: (product: Product) -> Unit,
    val onAddToBasketClick: (product: Product) -> Unit,
    val formatPrice: (price: Double) -> String
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var items = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_catalog_layout, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(newItems: List<Product>) {
        items = newItems
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            itemView.apply {
                Glide.with(catalogItemPicIv.context)
                    .load(product.imageUrl)
                    .error(R.drawable.ic_catalog_item_stub)
                    .into(catalogItemPicIv)
                catalogItemName.text = product.productName
                catalogItemDescr.text = product.description
                if (product.salePercent != 0) {
                    catalogItemMainPrice.text = formatPrice(product.discountPrice)
                    val rawPrice = formatPrice(product.price)
                    catalogItemRawPrice.visibility = View.VISIBLE
                    catalogItemRawPrice.text = getStrikethroughSpannable(rawPrice)
                } else {
                    catalogItemMainPrice.text = formatPrice(product.price)
                    catalogItemRawPrice.visibility = View.GONE
                }
                catalogItemRootCl.setOnClickListener {
                    onItemClick(items[adapterPosition])
                }
                catalogItemAddToBasketIv.setOnClickListener {
                    onAddToBasketClick(items[adapterPosition])
                }
            }
        }
    }

}
