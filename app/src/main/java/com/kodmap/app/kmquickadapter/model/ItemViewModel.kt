package com.kodmap.app.kmquickadapter.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import com.kodmap.app.library.listener.AdapterCallback

class ItemViewModel(app: Application) : AndroidViewModel(app) {
    var item = ObservableField<TestModel>()
    lateinit var listener: AdapterCallback

    fun setModel(model: TestModel) {
        item.set(model)
    }

    fun onClick() {
        listener.onClickListener(item.get(), -1)
    }

    fun onLongClick(): Boolean {
        listener.onLongClickListener(item.get(), -1)
        return true
    }

    fun initListener(listener: AdapterCallback) {
        this.listener = listener
    }
}