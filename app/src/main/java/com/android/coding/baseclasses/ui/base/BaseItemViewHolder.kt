package com.android.coding.baseclasses.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.android.coding.baseclasses.MyApplication
import com.android.coding.baseclasses.di.component.DaggerViewHolderComponent
import com.android.coding.baseclasses.di.component.ViewHolderComponent
import com.android.coding.baseclasses.di.module.ViewHolderModule
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 02-07-2019.
 *
 * We are making ItemViewHolder as lifecycleaware so we are adding
 * Observers, viewmodel and lifecycleregistry to get lifecycle,
 * thus making it a lifecycleOwner component
 *
 * Methods onCreate, onStart, onStop and onDestroy has lifecycleregistry states which will be called
 * when adapter's lifecycle method gets called initially
 */
abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
        @LayoutRes layoutId: Int, parent: ViewGroup)
    : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)), LifecycleOwner {

    init {
        onCreate() // initializing oncreate after constructor call to track the lifecycle states
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    // lifecycleregistry class keeps data of events and state of lifecycle so we can
    // get lifecycle from it

    @Inject
    lateinit var lifecycleRegistry: LifecycleRegistry

    @Inject
    lateinit var viewModel: VM

    // we need to update the associated viewmodel with data
    fun bind(data: T) {
        viewModel.updateData(data)
    }

    protected open fun setUpObservers() {
        viewModel.messageId.observe(this, Observer {
            showMessage(it)
        })

        viewModel.messageString.observe(this, Observer {
            showMessage(it)
        })
    }

    protected fun onCreate() {
        injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        setUpObservers()
        setUpView(itemView)
    }

    public fun onStart() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    public fun onStop() {
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    public fun onDestroy() {
        lifecycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    private fun buildViewHolderComponent() =
            DaggerViewHolderComponent.builder()
                    .applicationComponent((itemView.context.applicationContext as MyApplication).applicationComponent)
                    .viewHolderModule(ViewHolderModule(this)) // showing deprecated because nothing is been injected
                    .build()


    // message for livedata
    fun showMessage(message: String) = Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()

    // @StringRes to ensure that we get only string resource
    fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected abstract fun setUpView(view: View)

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)
}