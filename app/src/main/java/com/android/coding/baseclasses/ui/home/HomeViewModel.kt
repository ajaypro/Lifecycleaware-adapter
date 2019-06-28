package com.android.coding.baseclasses.ui.home

import androidx.lifecycle.MutableLiveData
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.ui.base.BaseViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable


class HomeViewModel (
        compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService,
        networkHelper: NetworkHelper)
    : BaseViewModel(compositeDisposable, networkHelper) {

    val data = MutableLiveData<String>()

    override fun onCreate() {

        data.postValue("HomeViewModel")

    }


}
