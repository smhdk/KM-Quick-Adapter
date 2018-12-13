package com.kodmap.app.kmquickadapter.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Intent
import com.kodmap.app.kmquickadapter.pagedlistAdapterSample.PagedlistAdapterSampleActivity
import com.kodmap.app.kmquickadapter.simpleAdapterSample.SimpleAdapterSampleActivity

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {

    fun rvAdapterSampleClick() {
        val intent = Intent(getApplication(), SimpleAdapterSampleActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent)
    }

    fun pagedlistAdapterSampleClick() {
        val intent = Intent(getApplication(), PagedlistAdapterSampleActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getApplication<Application>().startActivity(intent)
    }
}