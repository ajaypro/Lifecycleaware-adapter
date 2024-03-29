package com.android.coding.baseclasses.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class DatabaseInfo

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class NetworkInfo