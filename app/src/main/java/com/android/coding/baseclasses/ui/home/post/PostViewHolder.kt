package com.android.coding.baseclasses.ui.home.post

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import com.android.coding.baseclasses.R
import com.android.coding.baseclasses.di.component.ViewHolderComponent
import com.android.coding.baseclasses.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.item_view_layout.view.*

/**
 * Created by Ajay Deepak on 02-07-2019.
 */
class PostViewHolder(parent: ViewGroup): BaseItemViewHolder<Post, PostViewModel>(
        R.layout.item_view_layout, parent){

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }

    override fun setUpObservers() {
        super.setUpObservers()

        viewModel.data.observe(this, Observer {
            itemView.itemText.text = it.text
        })

        itemView.setOnClickListener {
            showMessage("$adapterPosition  clicked")
        }
    }

    override fun setUpView(view: View) {
        
    }

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
         viewHolderComponent.inject(this)
    }
}