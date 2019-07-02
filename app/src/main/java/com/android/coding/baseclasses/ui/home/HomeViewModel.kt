package com.android.coding.baseclasses.ui.home

import androidx.lifecycle.MutableLiveData
import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.ui.base.BaseViewModel
import com.android.coding.baseclasses.ui.home.post.Post
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable


class HomeViewModel (
        compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService,
        networkHelper: NetworkHelper)
    : BaseViewModel(compositeDisposable, networkHelper) {

    val data = MutableLiveData<List<Post>>()



    override fun onCreate() {

        data.postValue(listOf(
                Post("test1"),
                Post("test2"),
                Post("test3"),
                Post("test4"),
                Post("test5")
        ))

    }


}
