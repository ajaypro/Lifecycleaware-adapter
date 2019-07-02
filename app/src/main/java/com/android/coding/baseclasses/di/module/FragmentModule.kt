package com.android.coding.baseclasses.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.di.ActivityContext
import com.android.coding.baseclasses.ui.base.BaseFragment
import com.android.coding.baseclasses.ui.home.HomeViewModel
import com.android.coding.baseclasses.ui.home.post.PostAdapter
import com.android.coding.baseclasses.utils.NetworkHelper
import com.android.coding.baseclasses.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @ActivityContext
    @Provides
    fun provideContext(): Context = fragment.context!!

    @Provides
    fun providesHomeViewModel(
            compositeDisposable: CompositeDisposable,
            databaseService: DatabaseService,
            networkHelper: NetworkHelper,
            networkService: NetworkService
    ) : HomeViewModel = ViewModelProviders.of(fragment, ViewModelProviderFactory(HomeViewModel::class){
        HomeViewModel(compositeDisposable,
                databaseService,
                networkService,
                networkHelper)
    }).get(HomeViewModel::class.java)

    @Provides
    fun providesPostAdapter(): PostAdapter = PostAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun providesLinearLayoutManager() = LinearLayoutManager(fragment.context)


}
