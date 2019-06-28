package com.android.coding.baseclasses.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.android.coding.baseclasses.R
import com.android.coding.baseclasses.di.component.FragmentComponent
import com.android.coding.baseclasses.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeViewModel>() {

    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.data.observe(this, Observer {
            tv_message.text = it
        })
    }

    override fun provideLayoutId() = R.layout.fragment_home

    override fun setUpView(view: View) {
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    companion object {

        val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}
