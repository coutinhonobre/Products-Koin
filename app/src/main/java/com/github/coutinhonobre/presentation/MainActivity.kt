package com.github.coutinhonobre.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import coil.api.load
import com.github.coutinhonobre.R
import com.github.coutinhonobre.model.General
import com.github.coutinhonobre.util.LoadingState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu_principal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel by viewModel<ProductViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel.data.observe(this, Observer {
            // Populate the UI

            spotlightUI(it)

            cashUI(it)

            productsUI(it)

        })

        userViewModel.loadingState.observe(this, Observer {
            // Observe the loading state
            loadAndStateUI(it)
        })

        swipeRefreshMain.setOnRefreshListener {
            userViewModel.restart()
        }
    }

    private fun loadAndStateUI(it: LoadingState) {
        when (it.status) {
            LoadingState.Status.RUNNING -> {
                viewFlipperMain.displayedChild = 0
                swipeRefreshMain.isRefreshing = false
            }
            LoadingState.Status.SUCCESS -> {
                viewFlipperMain.displayedChild = 1
                swipeRefreshMain.isRefreshing = false
            }
            LoadingState.Status.FAILED -> {
                viewFlipperMain.displayedChild = 2
                textViewMainError.text = it.msg
                swipeRefreshMain.isRefreshing = false
            }
        }
    }

    private fun MainActivity.productsUI(it: General) {
        with(it.products) {
            with(recyclerViewProdutos) {
                layoutManager =
                        GridLayoutManager(this@MainActivity, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = ProdutosAdapter(it.products)
            }
        }
    }

    private fun MainActivity.cashUI(it: General) {
        with(it.cash) {
            imageViewDigioCash.load(it.cash.bannerURL) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                imageViewDigioCash.scaleType = ImageView.ScaleType.FIT_XY
            }
        }
    }

    private fun MainActivity.spotlightUI(it: General) {
        with(it.spotlight) {
            with(recyclerViewSpotLight) {
                layoutManager =
                        GridLayoutManager(this@MainActivity, 1, GridLayoutManager.HORIZONTAL, false)
                adapter = SpotlightAdapter(it.spotlight)
            }
        }
    }
}