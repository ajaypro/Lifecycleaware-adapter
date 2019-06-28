package com.android.coding.baseclasses.utils

import android.content.Context

import com.android.coding.baseclasses.di.ApplicationContext

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(
        // Should be Application Context
        @ApplicationContext private val context: Context) {

    // will check for network connectivity
    val isNetworkConnected: Boolean
        get() = false
}
