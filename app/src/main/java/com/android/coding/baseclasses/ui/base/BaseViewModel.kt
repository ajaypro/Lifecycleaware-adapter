package com.android.coding.baseclasses.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.coding.baseclasses.utils.NetworkHelper
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ajay Deepak on 28-06-2019.
 */
abstract class BaseViewModel(
       protected val compositeDisposable: CompositeDisposable,
       protected val networkHelper: NetworkHelper
): ViewModel() {

    val messageId = MutableLiveData<Int>() // used to pick error messages with R.String. defined in xml
    val messageString = MutableLiveData<String>() // to display error message from server. 
    
    protected fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected

    override fun onCleared() {
        if(!compositeDisposable.isDisposed){
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

    // will be implemented by other viewmodel and tells the ui to create the viewmodel
    abstract fun onCreate()
}