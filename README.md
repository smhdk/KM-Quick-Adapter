
KM Quick Adapter with Databinding
=============
[![Release](https://jitpack.io/v/smhdk/KM-Popup-Image-Slider.svg)](https://jitpack.io/#smhdk/KM-Popup-Image-Slider "![Release](https://jitpack.io/v/smhdk/KM-Popup-Image-Slider.svg)")

You can easyly crate RecyclerviewAdapter and PagedlistAdapter using databinding with this library.

You can take a look [here](https://developer.android.com/topic/libraries/architecture/paging/) for Android PagingLibrary. Pagedlist and PagedlistAdapter are components of PagingLibrary.

# Usage

Create RecyclerviewAdapter or PagedlistAdapter with KmBuilder

RecyclerView Adapter: 
```kotlin
     val adapter = KmBuilder.getRvAdapter()
            .createBinding { parent, viewType ->
                //Create ViewDataBinding variable for your layout and return it
                val mBinding: ItemAdapterBinding = inflate(
                    LayoutInflater.from(context),
                    R.layout.item_adapter,
                    parent,
                    false
                )
                mBinding.viewModel = ItemViewModel(aplicationContext)
                mBinding.viewModel?.initListener(this)
                mBinding
            }
            .bindItem { binding, model ->
                //Set item to your item layout view model
                (binding as ItemAdapterBinding).viewModel?.setModel(model as TestModel)
            }
            .setDiffCallback { newList, oldList ->
                //You can set DiffUtil.Callback to adapter
                KmDiffCallback(newList, oldList)
            }
            .build()
```

PagedlistAdapter: 
```kotlin
       val adapter = KmBuilder.getPagedListAdapter()
            .createBinding { parent, viewType ->
                val mBinding: ItemAdapterBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(context),
                    R.layout.item_adapter,
                    parent,
                    false
                )
                mBinding.viewModel = ItemViewModel(applicationContext)
                mBinding.viewModel?.initListener(this)
                mBinding
            }
            .bindItem { binding, model ->
                (binding as ItemAdapterBinding).viewModel?.setModel(model as TestModel)
            }
            .setDiffCallback { 
                //You must return DiffUtil.ItemCallback
                KmPagedlistAdapter.Companion.KmDiffItemCallback() 
            }
            .build()
```

Set adapter to your recyclerview: 
```kotlin
      recyclerView.adapter = kmPagedListAdapter
```

And the set list to adapter: 
```kotlin
      //For PagedlistAdapter
      (recyclerView.adapter as KmPagedlistAdapter).setList(pagedList as PagedList<Any>)

      //For RecyclerviewAdapter
      (recyclerView.adapter as KmRvAdapter).setList(list)

```
### How is works with multi view type?
Implement createBinding method. Create ViewDataBinding object for each view type and return it.
```kotlin
      .createBinding { parent, viewType ->  
		  lateinit var binding: ViewDataBinding  
		  when (viewType) {  
			  0 -> {  
				  binding = inflate(  
					  LayoutInflater.from(this@SimpleAdapterSampleActivity),  
		              R.layout.item_adapter,  
		              parent,  
		              false)}  
		      1 -> {  
				  binding = inflate(  
					  LayoutInflater.from(this@SimpleAdapterSampleActivity),  
		              R.layout.item_adapter,  
		              parent,  
		              false)}
		  return binding
	  }
```
### How is implement DiffUtil.ItemCallback for PagedlistAdapter
```kotlin
      val itemCallback = object : DiffUtil.ItemCallback<Any>() {  
		  override fun areItemsTheSame(oldItem: Any, newItem: Any) =  
		        oldItem == newItem  
  
		  override fun areContentsTheSame(oldItem: Any, newItem: Any) =  
		        oldItem == newItem  
	  }
```
.setDiffCallback method must return DiffUtil.ItemCallback from for PagedlistAdapter,must not be null.

### How is implement DiffUtil.DiffCallback for RecyclerViewAdapter
```kotlin
      fun diffCallback(newList: List<Any>, oldList: List<Any>) = object : DiffUtil.Callback(){  
		  override fun areItemsTheSame(p0: Int, p1: Int) = newList[p0] == oldList[p0]  
  
		  override fun getOldListSize() = oldList.size  
  
		  override fun getNewListSize() = newList.size  
  
		  override fun areContentsTheSame(p0: Int, p1: Int) = newList[p0] == oldList[p0]  
}
```
.setDiffCallback method must return DiffUtil.Callback from for RecyclerViewAdapter. If you don't implement this method,default DiffUtilCallback works like example.



# Download

##### Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
##### Step 2. Add the dependency
Add it in app build.gradle
```
dependencies {
	    compile 'com.github.smhdk:KM-Quick-Adapter:v1.0.0'
	}
```

# Licence

	MIT License

	Copyright (c) 2018 Semih Dik

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
