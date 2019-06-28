package com.android.coding.baseclasses.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * Created by Ajay Deepak on 28-06-2019.
 */
class ViewModelProviderFactory<T: ViewModel>(
        private val kClass: KClass<T>,
        private val creator: () -> T
):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw  IllegalArgumentException("Unknown class name")

    }
}