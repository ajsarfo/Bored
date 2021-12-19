package com.sarftec.bored.view.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.sarftec.bored.R
import com.sarftec.bored.databinding.ActivityDetailBinding
import com.sarftec.bored.view.advertisement.AdCountManager
import com.sarftec.bored.view.advertisement.BannerManager
import com.sarftec.bored.view.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private val layoutBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel by viewModels<DetailViewModel>()

    override fun createAdCounterManager(): AdCountManager {
        return AdCountManager(listOf(1, 3, 2))
    }

    override fun canShowInterstitial(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutBinding.root)
        /*************** Admob Configuration ********************/
        BannerManager(this, adRequestBuilder).attachBannerAd(
            getString(R.string.admob_banner_main),
            layoutBinding.mainBanner
        )
        /**********************************************************/
        viewModel.getTask()
        viewModel.screenState.observe(this) {
            observeState(it)
        }
        layoutBinding.back.setOnClickListener { onBackPressed() }
        layoutBinding.button.setOnClickListener {
            interstitialManager?.showAd {
                viewModel.getTask()
            }
        }
        layoutBinding.networkError.reload.setOnClickListener {
            viewModel.getTask()
        }
    }

    private fun observeState(state: DetailViewModel.ScreenState) {
        when(state) {
            is DetailViewModel.ScreenState.Loading -> {
                layoutBinding.networkError.parent.visibility = View.GONE
                layoutBinding.detailContent.visibility = View.GONE
                layoutBinding.progress.visibility = View.VISIBLE
            }
            is DetailViewModel.ScreenState.Error -> {
                layoutBinding.networkError.parent.visibility = View.VISIBLE
                layoutBinding.progress.visibility = View.GONE
                layoutBinding.detailContent.visibility = View.GONE
            }
            is DetailViewModel.ScreenState.IncomingTask -> {
                layoutBinding.progress.visibility = View.GONE
                layoutBinding.networkError.parent.visibility = View.GONE
                layoutBinding.detailContent.visibility = View.VISIBLE
              layoutBinding.apply {
                  task.text = state.task.activity
                  category.text = getString(R.string.activity_title, state.task.type)
                  price.text = getString(R.string.difficulty, (state.task.price * 100).toInt())
                  price.text = "This activity is ${(state.task.price * 100).toInt()}% more difficult than others"
                 participants.text = "you need ${if(state.task.participants == 1) "${state.task.participants} person" else "+${state.task.participants} people"} to do it"
              }
            }
        }
    }
}