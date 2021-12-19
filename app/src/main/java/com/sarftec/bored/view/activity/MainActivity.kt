package com.sarftec.bored.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.gms.ads.AdRequest
import com.sarftec.bored.R
import com.sarftec.bored.databinding.ActivityMainBinding
import com.sarftec.bored.view.advertisement.BannerManager
import com.sarftec.bored.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val layoutBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutBinding.root)
        /*************** Admob Configuration ********************/
        BannerManager(this, adRequestBuilder).attachBannerAd(
            getString(R.string.admob_banner_main),
            layoutBinding.mainBanner
        )
        /**********************************************************/
        setUpButtonListeners()
        viewModel.screenState.observe(this) { event ->
            event.getIfNotHandled()?.let { handleEvent(it) }
        }
    }

    private fun setUpButtonListeners() {
        layoutBinding.getTask.setOnClickListener {
            viewModel.getTask()
        }
    }

    private fun handleEvent(event: MainViewModel.ScreenState) {
        when(event) {
            MainViewModel.ScreenState.GetTask -> {
                navigateTo(DetailActivity::class.java)
            }
        }
    }
}