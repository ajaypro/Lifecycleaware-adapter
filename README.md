## Lifecycleaware adapter

* Making use of the adapter lifecycle methods such as `onViewAttachedToWindow`, `onViewDetachedToWindow`, `onAttachedToRecyclerView` and 
 `onDetachedFromRecyclerView` to get the states 
* Making the viewholder lifecycleaware by initializing lifecycleregistry and creating methods such as 
  ```
    onCreate()
	onStart()
	onStop()
	onDestroy()
  ```
* Implementing each method with respective lifecycle states  such as INITIALIZED, STARTED, RESUMED, DESTROYED, now these methods would be 
  called in Baseadapter's lifecycle methods such as
    ```
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
	```
* Now to make adapter aware of the changes of ui e.g if user clicks home button then activity/fragment would go to onStop therefore the adapter 
  should also not receive any updates from viewmodel, for that adapter should get the lifecycle state of its activity which it can send to 
  its viewholder as well so that its been state is paused. 