package com.android.coding.baseclasses.ui.base

import androidx.lifecycle.MutableLiveData
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ajay Deepak on 02-07-2019.
 */

abstract class BaseItemViewModel<T: Any>(compositeDisposable: CompositeDisposable,
                                         networkHelper: NetworkHelper)
    :BaseViewModel(compositeDisposable, networkHelper) {

    val data = MutableLiveData<T>()

    fun updateData(data: T){
        this.data.postValue(data)
    }

    /**
     * Manual clear as this ItemViewModel will be used by CustomViewHolder(ItemViewHolder) which is not
     * lifecycleaware component, therefore we clear it manually
     */

   fun onManualCleared() = onCleared()
}