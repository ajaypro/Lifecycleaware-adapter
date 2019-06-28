package com.android.coding.baseclasses.di.module

import android.content.Context

import com.android.coding.baseclasses.MyApplication
import com.android.coding.baseclasses.di.ApplicationContext
import com.android.coding.baseclasses.di.DatabaseInfo
import com.android.coding.baseclasses.di.NetworkInfo

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(private val application: MyApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context = application

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String = "dummy_db"

    @Provides
    @DatabaseInfo
    fun provideDatabaseVersion(): Int = 1

    @Provides
    @NetworkInfo
    fun provideApiKey(): String = "SOME_API_KEY"

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}
