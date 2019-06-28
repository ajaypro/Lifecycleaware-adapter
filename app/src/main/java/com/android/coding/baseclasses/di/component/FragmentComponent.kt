package com.android.coding.baseclasses.di.component

import com.android.coding.baseclasses.di.module.FragmentModule
import com.android.coding.baseclasses.di.FragmentScope
import com.android.coding.baseclasses.ui.home.HomeFragment

import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
}
