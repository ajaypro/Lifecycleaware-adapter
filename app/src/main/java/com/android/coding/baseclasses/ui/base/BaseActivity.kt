package com.android.coding.baseclasses.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.coding.baseclasses.MyApplication
import com.android.coding.baseclasses.di.component.ActivityComponent
import com.android.coding.baseclasses.di.component.DaggerActivityComponent
import com.android.coding.baseclasses.di.module.ActivityModule
import javax.inject.Inject

/**
 * Created by Ajay Deepak on 28-06-2019.
 */
abstract class BaseActivity<VM: BaseViewModel>: AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setUpObservers()
        setUpView(savedInstanceState)
        setContentView(provideLayoutId())
        viewModel.onCreate()
    }

    // Setting up observers for livedata for the error messages
    protected open fun setUpObservers(){
        viewModel.messageId.observe(this, Observer {
          showMessage(it)
        })

        viewModel.messageString.observe(this, Observer {
           showMessage(it)
        })
    }

    private fun buildActivityComponent() =
            DaggerActivityComponent.builder()
                    .applicationComponent((application as MyApplication).applicationComponent)
                    .activityModule(ActivityModule(this)) // showing deprecated because nothing is been utilized in injection
                    .build()


     // message for livedata
    fun showMessage(message:String) = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    // @StringRes to ensure that we get only string resource
    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    @LayoutRes // for inflating layout in each activity without using setContentView
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setUpView(savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

}