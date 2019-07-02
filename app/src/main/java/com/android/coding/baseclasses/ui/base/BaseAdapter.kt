package com.android.coding.baseclasses.ui.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.coding.baseclasses.ui.home.post.Post

/**
 * Created by Ajay Deepak on 02-07-2019.
 *
 * We use the lifecycle methods of adapter such as
 *   onViewAttachedToWindow
 *   onViewDetachedToWindow to get the viewholder's states on each method
 *
 *   These below methods of recyclerview will observe RV attached and detached to adapter.
 *   onAttachedToRecyclerView
 *   onDetachedFromRecyclerView
 *
 *   We have added `parentLifecycle` of an activity to get the lifecycleevents of parent using which
 *   we will use the adapter states e.g if activity reaches onStop then viewholder and adapter should
 *   have onStop event triggered.
 */
abstract class BaseAdapter<T : Any, VH : BaseItemViewHolder<T, out BaseItemViewModel<T>>>(
        parentLifecycle: Lifecycle,
        private val dataList: ArrayList<T>) : RecyclerView.Adapter<VH>() {

    private var recyclerView: RecyclerView? = null

    init {
        parentLifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onParentDestroy() {
                recyclerView?.run {
                    for (i in 0 until childCount) {
                        getChildAt(i)?.let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).run {
                                // cast it to BaseItemViewHolder
                                onDestroy() // itemViewHolder destroy()
                                viewModel.onManualCleared() // clearing the viewmodel data
                            }

                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onParentStop() {
                recyclerView?.run {
                    for (i in 0 until childCount) {
                        getChildAt(i)?.let {
                            (getChildViewHolder(it) as BaseItemViewHolder<*, *>).run {
                                // cast it to BaseItemViewHolder
                                onStop() // itemViewHolder onStop()
                            }

                        }
                    }
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_START)
            fun onParentStart() {
                recyclerView?.run {
                    if (layoutManager is LinearLayoutManager) {

                        // getting first visible item
                        val firstItem = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                        // getting last visible item
                        val lastItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()


                        if (firstItem in 0..lastItem) {

                            // for all visible items check for viewholder position and call
                            // onStart() of itemViewHolder, so that viewholder gets the data

                            for (i in firstItem..lastItem) {
                                findViewHolderForAdapterPosition(i)?.let {
                                    (it as BaseItemViewHolder<*, *>).onStart()
                                }
                            }
                        }
                    }

                }
            }


        })
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onStop()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])
    }

    // To update only updated data
    fun appendData(postList: List<T>) {
        val oldCount = itemCount // count before we loading data
        this.dataList.addAll(postList)
        val currentCount = itemCount // count with new data

        if(oldCount == 0 && currentCount >0 ){
            notifyDataSetChanged()
        } else if(oldCount > 0 && currentCount > oldCount){
            notifyItemRangeChanged(oldCount -1, currentCount - oldCount)
        }

    }


    override fun getItemCount(): Int = dataList.size


}