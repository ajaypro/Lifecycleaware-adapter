package com.android.coding.baseclasses.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.coding.baseclasses.R
import com.android.coding.baseclasses.di.component.FragmentComponent
import com.android.coding.baseclasses.ui.base.BaseAdapter
import com.android.coding.baseclasses.ui.base.BaseFragment
import com.android.coding.baseclasses.ui.home.post.PostAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>() {

    @Inject
    lateinit var postAdapter: PostAdapter

    @Inject
    lateinit var linearlayoutManager: LinearLayoutManager


    override fun setUpObservers() {
        super.setUpObservers()
        viewModel.data.observe(this, Observer {
              postAdapter.appendData(it)
        })
    }



    override fun provideLayoutId() = R.layout.fragment_home

    override fun setUpView(view: View) {
        rv_posts.apply {
            adapter = postAdapter
            layoutManager =  linearlayoutManager
        }

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
