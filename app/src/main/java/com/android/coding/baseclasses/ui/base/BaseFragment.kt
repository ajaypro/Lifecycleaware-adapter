package com.android.coding.baseclasses.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.android.coding.baseclasses.MyApplication
import com.android.coding.baseclasses.di.component.DaggerFragmentComponent
import com.android.coding.baseclasses.di.component.FragmentComponent
import com.android.coding.baseclasses.di.module.FragmentModule
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 28-06-2019.
 */
abstract class BaseFragment<VM: BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    // Setting up observers for livedata for the error messages
    protected open fun setUpObservers(){
        viewModel.messageId.observe(this, Observer {
            showMessage(it)
        })

        viewModel.messageString.observe(this, Observer {
            showMessage(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setUpObservers()
        viewModel.onCreate()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(provideLayoutId(), container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun buildActivityComponent() =
            DaggerFragmentComponent.builder()
                    .applicationComponent((context!!.applicationContext as MyApplication).applicationComponent)
                    .fragmentModule(FragmentModule(this)) // showing deprecated because nothing is been utilized in injection
                    .build()


    // message for livedata
    fun showMessage(message:String) = Toast.makeText(context!!.applicationContext, message, Toast.LENGTH_SHORT).show()

    // @StringRes to ensure that we get only string resource
    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    @LayoutRes // for inflating layout in each activity without using setContentView
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setUpView(view: View)

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

}