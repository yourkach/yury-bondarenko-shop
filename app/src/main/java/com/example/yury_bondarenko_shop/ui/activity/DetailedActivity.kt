package com.example.yury_bondarenko_shop.ui
.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.Product
import com.example.yury_bondarenko_shop.presenter.DetailedPresenterFactory
import com.example.yury_bondarenko_shop.presenter.DetailedView
import com.example.yury_bondarenko_shop.strikethroughSpannable
import kotlinx.android.synthetic.main.activity_detailed.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class DetailedActivity : MvpAppCompatActivity(), DetailedView {

    @Inject
    lateinit var detailedPresenter: DetailedPresenterFactory

    private val presenter by moxyPresenter { detailedPresenter.create(product, launchedFromBasket) }

    lateinit var product: Product

    private var launchedFromBasket: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        product = intent?.getParcelableExtra(DETAILED_PRODUCT_KEY)!!
        launchedFromBasket = intent.getStringExtra(DETAILED_LAUNCHED_FROM)
            ?.let { it == BasketActivity::class.java.simpleName } ?: false
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
        setListeners()
    }

    private fun setListeners() {
        detailedBackIv.setOnClickListener {
            finish()
        }
        detailedOpenBasketIv.setOnClickListener {
            presenter.onBasketClicked()
        }
        detailedAddToBasketBtn.setOnClickListener {
            presenter.onAddToBasketClick(product)

        }
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResume()
    }


    override fun startBasketActivity() {
        startActivity(Intent(this, BasketActivity::class.java))
    }

    override fun loadProductImage(imgUrl: String) {
        Glide
            .with(detailedPicIv.context)
            .load(imgUrl)
            .error(R.mipmap.ic_launcher)
            .into(detailedPicIv)
    }

    override fun setProductDescription(description: String) {
        detailedDescriptionTv.text = description
    }

    override fun setProductAttributes(text: String) {
        detailedAttributesTv.text = text
    }

    override fun setProductPrice(mainPrice: String) {
        detailedMainPriceTv.text = mainPrice
    }

    override fun setProductPrice(discountPrice: String, rawPrice: String) {
        detailedMainPriceTv.text = discountPrice
        detailedRawPriceTv.apply {
            visibility = View.VISIBLE
            text = rawPrice.strikethroughSpannable
        }
    }

    override fun setProductName(name: String) {
        detailedNameTv.text = name
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(this, getString(stringResId), Toast.LENGTH_SHORT).show()
    }


    override fun setBasketCountLabelVisibility(visible: Boolean) {
        detailedBasketItemsCountTv.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setBasketCountText(text: String) {
        detailedBasketItemsCountTv.text = text
    }

    override fun setBasketWidgetsInvisible() {
        detailedBasketItemsCountTv.visibility = View.GONE
        detailedOpenBasketIv.visibility = View.GONE
    }


    companion object {
        const val DETAILED_PRODUCT_KEY = "DETAILED_PRODUCT_KEY"
        const val DETAILED_LAUNCHED_FROM = "DETAILED_LAUNCHED_FROM"
    }
}
