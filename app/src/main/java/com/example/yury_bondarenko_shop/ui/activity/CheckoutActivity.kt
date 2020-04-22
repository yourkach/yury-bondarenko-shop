package com.example.yury_bondarenko_shop.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.yury_bondarenko_shop.ui.presenter.ProductPresenter
import com.example.yury_bondarenko_shop.view.ProductView
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.ui.activity.CatalogActivity.Companion.PRODUCT_ID
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity(),
    ProductView {

    private val presenter =
        ProductPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        presenter.attachView(this)
        setListeners()

        val productId = intent.extras?.getInt(PRODUCT_ID, -1)

        Log.i("idProduct", productId.toString())

    }

    private fun setListeners() {
        checkoutBackIv.setOnClickListener {
            finish()
        }
        checkoutFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkFirstName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutMiddleName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkMiddleName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkLastName(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        checkoutPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.checkPhoneNumber(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    private fun EditText.showErrorIcon(visible: Boolean) {
        val drawable = if (visible) R.drawable.ic_error else 0

        this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun print(price: Double) {
        Log.i("productView", price.toString())
    }

    override fun print(text: String) {
        Log.i("productView", text)
    }

    override fun showErrorLastName(visible: Boolean) {
        checkoutLastName.showErrorIcon(visible)
    }

    override fun showErrorFirstName(visible: Boolean) {
        checkoutFirstName.showErrorIcon(visible)
    }

    override fun showErrorMiddleName(visible: Boolean) {
        checkoutMiddleName.showErrorIcon(visible)
    }

    override fun showErrorPhone(visible: Boolean) {
        checkoutPhoneNumber.showErrorIcon(visible)
    }


    /*
    fun setRawPrice(formattedPrice: String) {
        checkoutRawPrice.text = formattedPrice
    }

    fun setDiscount(formattedDiscount: String) {
        checkoutDiscountAmount.text = formattedDiscount
    }

    fun setTotalPrice(formattedPrice: String) {
        checkoutTotalPrice.text = formattedPrice
    }

    fun setBasketItemsCount(count: Int) {
        checkoutProductsCount.text = "($count)"
    }
    */
}


