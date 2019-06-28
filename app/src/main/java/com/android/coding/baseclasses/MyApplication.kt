package com.android.coding.baseclasses

import android.app.Application

import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.di.component.ApplicationComponent
import com.android.coding.baseclasses.di.component.DaggerApplicationComponent
import com.android.coding.baseclasses.di.module.ApplicationModule

import javax.inject.Inject

class MyApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var networkService: NetworkService

    @Inject
    lateinit var databaseService: DatabaseService

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }
}