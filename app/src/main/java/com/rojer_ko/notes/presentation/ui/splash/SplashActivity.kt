package com.rojer_ko.notes.presentation.ui.splash

import android.os.Handler
import com.rojer_ko.notes.presentation.ui.base.BaseActivity
import com.rojer_ko.notes.presentation.ui.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel


private const val START_DELAY = 1000L

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

//    override val viewModel: SplashViewModel by lazy {
//        ViewModelProviders.of(this).get(SplashViewModel::class.java)
//    }
    override val model: SplashViewModel by viewModel()
    override val layoutRes: Int = com.rojer_ko.notes.R.layout.activity_splash

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ model.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf{ it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
