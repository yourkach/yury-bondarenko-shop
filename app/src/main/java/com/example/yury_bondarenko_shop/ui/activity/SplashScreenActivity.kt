package com.example.yury_bondarenko_shop.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.yury_bondarenko_shop.App
import com.example.yury_bondarenko_shop.R
import com.example.yury_bondarenko_shop.domain.model.remote.RemoteCategory
import com.example.yury_bondarenko_shop.presenter.SplashPresenter
import com.example.yury_bondarenko_shop.presenter.SplashView
import com.example.yury_bondarenko_shop.ui.activity.CategoriesActivity.Companion.CATEGORIES_LIST_TAG
import kotlinx.android.synthetic.main.activity_splash_screen.*
import moxy.ktx.moxyPresenter
import kotlinx.coroutines.*
import moxy.MvpAppCompatActivity
import javax.inject.Inject

class SplashScreenActivity : MvpAppCompatActivity(), SplashView {

    @Inject
    lateinit var splashPresenter: SplashPresenter

    private val presenter by moxyPresenter { splashPresenter }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun showMessage(stringResId: Int) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()
    }

    override fun startCategoriesActivity(categories: List<RemoteCategory>) {
        val intent = Intent(this, CategoriesActivity::class.java)
        intent.putParcelableArrayListExtra(CATEGORIES_LIST_TAG, ArrayList(categories))
        startActivity(intent)
        splashLoadingBar.visibility = View.GONE
        finish()
    }
}
