package com.android.coding.baseclasses.di.component

import com.android.coding.baseclasses.di.module.ActivityModule
import com.android.coding.baseclasses.di.ActivityScope
import com.android.coding.baseclasses.ui.main.MainActivity

import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}
