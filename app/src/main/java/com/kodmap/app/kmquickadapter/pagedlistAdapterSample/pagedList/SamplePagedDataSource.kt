package com.kodmap.app.kmquickadapter.pagedlistAdapterSample.pagedList

import android.arch.paging.PageKeyedDataSource
import com.kodmap.app.kmquickadapter.model.TestModel

class SamplePagedDataSource : PageKeyedDataSource<Int, TestModel>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, TestModel>) {
        val list = arrayListOf<TestModel>()
        for (i in 0..20) {
            list.add(TestModel("Item $i"))
        }
        callback.onResult(list, 0, list.size)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TestModel>) {
        val list = arrayListOf<TestModel>()
        for (i in params.key..params.key + 20) {
            list.add(TestModel("Item $i"))
        }
        callback.onResult(list, params.key + 21)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TestModel>) {

    }

}