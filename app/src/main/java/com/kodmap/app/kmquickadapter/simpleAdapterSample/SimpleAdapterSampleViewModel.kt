package com.kodmap.app.kmquickadapter.simpleAdapterSample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.kodmap.app.kmquickadapter.model.TestModel


class SimpleAdapterSampleViewModel(app: Application) : AndroidViewModel(app) {
    fun getList(): List<TestModel> {
        val list = mutableListOf<TestModel>()
        for (i in 0..100) {
            val testModel = TestModel("Item $i")
            list.add(testModel)
        }
        return list
    }
}