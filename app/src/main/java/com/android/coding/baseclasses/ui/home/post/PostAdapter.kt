package com.android.coding.baseclasses.ui.home.post

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.android.coding.baseclasses.ui.base.BaseAdapter

/**
 * Created by Ajay Deepak on 02-07-2019.
 */

class PostAdapter(parentLifecycle: Lifecycle, postList: ArrayList<Post>)
    : BaseAdapter<Post, PostViewHolder>(parentLifecycle, postList) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  PostViewHolder(parent)
}