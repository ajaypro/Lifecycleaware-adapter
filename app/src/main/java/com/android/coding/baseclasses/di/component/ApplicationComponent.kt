package com.android.coding.baseclasses.di.component

import android.content.Context
import com.android.coding.baseclasses.MyApplication
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.di.ApplicationContext
import com.android.coding.baseclasses.di.module.ApplicationModule
import com.android.coding.baseclasses.utils.NetworkHelper
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MyApplication)

    // Methods in this component will be provided to the activity and fragment modules

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getNetworkHelper(): NetworkHelper

    fun getCompositeDisposable(): CompositeDisposable
}
