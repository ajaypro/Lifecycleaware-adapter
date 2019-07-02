package com.android.coding.baseclasses.di.module

import android.content.Context
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.di.ActivityContext
import com.android.coding.baseclasses.di.ViewHolderScope
import com.android.coding.baseclasses.ui.base.BaseFragment
import com.android.coding.baseclasses.ui.base.BaseItemViewHolder
import com.android.coding.baseclasses.ui.home.HomeViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import com.android.coding.baseclasses.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewHolderModule(private val viewholder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewHolderScope // will be provided only for Viewholders
    fun providesLifeCycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewholder)

}
