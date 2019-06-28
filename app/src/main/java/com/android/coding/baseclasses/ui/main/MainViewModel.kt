package com.android.coding.baseclasses.ui.main

import androidx.lifecycle.MutableLiveData
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.ui.base.BaseViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

class MainViewModel (
        compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        networkHelper: NetworkHelper,
        private val networkService: NetworkService)
    : BaseViewModel(compositeDisposable, networkHelper) {

    val data = MutableLiveData<String>()

    override fun onCreate() {
        data.postValue("MainViewModel")
    }
}
