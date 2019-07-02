package com.android.coding.baseclasses.ui.home.post

import com.android.coding.baseclasses.data.local.DatabaseService
import com.android.coding.baseclasses.data.remote.NetworkService
import com.android.coding.baseclasses.ui.base.BaseItemViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 02-07-2019.
 *
 * Using constructor injection  as we are using viewmodel providers to supply constructor arguments
 * unlike in activities and fragments
 */
class PostViewModel @Inject constructor(compositeDisposable: CompositeDisposable,
                    networkHelper: NetworkHelper,
                    private val databaseService: DatabaseService,
                    private val networkService: NetworkService)
    : BaseItemViewModel<Post>(compositeDisposable, networkHelper) {

    override fun onCleared() {
        super.onCleared()

    }

    override fun onCreate() {

    }
}