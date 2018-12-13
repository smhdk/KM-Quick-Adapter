package com.kodmap.app.kmquickadapter.pagedlistAdapterSample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.kodmap.app.kmquickadapter.model.TestModel
import com.kodmap.app.kmquickadapter.pagedlistAdapterSample.pagedList.SamplePagedDataSource

class PagedlistAdapterSampleViewModel(app: Application) : AndroidViewModel(app) {
    fun getList(): LiveData<PagedList<TestModel>> {
        val dataSourceFactory = object : android.arch.paging.DataSource.Factory<Int, TestModel>() {
            override fun create(): DataSource<Int, TestModel>? =
                SamplePagedDataSource()
        }
        val pagedListBuilder = LivePagedListBuilder(
            dataSourceFactory, PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()
        )

        return pagedListBuilder.build()
    }
}