package com.android.coding.baseclasses.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.di.ActivityContext
import com.android.coding.baseclasses.ui.base.BaseActivity
import com.android.coding.baseclasses.ui.main.MainViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import com.android.coding.baseclasses.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) { // * as we don't know what type as of now

    @ActivityContext
    @Provides
    fun provideContext(): Context = activity

    @Provides
    fun providesMainViewModel(
            compositeDisposable: CompositeDisposable,
            databaseService: DatabaseService,
            networkHelper: NetworkHelper,
            networkService: NetworkService
    ) : MainViewModel = ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class){
        MainViewModel(compositeDisposable, databaseService, networkHelper, networkService)
    }).get(MainViewModel::class.java)


}
