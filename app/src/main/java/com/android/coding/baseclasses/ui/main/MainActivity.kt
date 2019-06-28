package com.android.coding.baseclasses.ui.main

import android.os.Bundle

import com.android.coding.baseclasses.R
import com.android.coding.baseclasses.ui.home.HomeFragment

import androidx.lifecycle.Observer
import com.android.coding.baseclasses.di.component.ActivityComponent
import com.android.coding.baseclasses.ui.base.BaseActivity
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.data.observe(this, Observer {
            tv_message.text = it
        })
    }

    override fun provideLayoutId() = R.layout.activity_main

    override fun setUpView(savedInstanceState: Bundle?) {
        addHomeFragment()
    }

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    private fun addHomeFragment() {
        if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container_fragment, HomeFragment.newInstance(), HomeFragment.TAG)
                    .commit()
        }
    }


}
