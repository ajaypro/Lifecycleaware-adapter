package com.android.coding.baseclasses.di.component

import androidx.recyclerview.widget.RecyclerView
import com.android.coding.baseclasses.di.module.FragmentModule
import com.android.coding.baseclasses.di.FragmentScope
import com.android.coding.baseclasses.di.ViewHolderScope
import com.android.coding.baseclasses.di.module.ViewHolderModule
import com.android.coding.baseclasses.ui.home.HomeFragment
import com.android.coding.baseclasses.ui.home.post.PostViewHolder

import dagger.Component

@ViewHolderScope
@Component(dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class])
interface ViewHolderComponent {

       fun inject(postViewHolder: PostViewHolder)

}
