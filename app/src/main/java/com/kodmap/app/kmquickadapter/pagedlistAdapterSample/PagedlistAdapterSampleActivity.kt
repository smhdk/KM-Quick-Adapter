package com.kodmap.app.kmquickadapter.pagedlistAdapterSample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.kodmap.app.kmquickadapter.R
import com.kodmap.app.kmquickadapter.databinding.ActivityPagedlistSampleBinding
import com.kodmap.app.kmquickadapter.databinding.ItemAdapterBinding
import com.kodmap.app.kmquickadapter.model.ItemViewModel
import com.kodmap.app.kmquickadapter.model.TestModel
import com.kodmap.app.library.adapter.pagedlistAdapter.KmPagedlistAdapter
import com.kodmap.app.library.KmBuilder
import com.kodmap.app.library.listener.AdapterCallback

class PagedlistAdapterSampleActivity() : AppCompatActivity(), AdapterCallback {

    private lateinit var mBinding: ActivityPagedlistSampleBinding
    private lateinit var kmPagedListAdapter: KmPagedlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagedlist_sample)

        initBinding()

        initAdapter()

        setList()
    }

    private fun setList() {
        mBinding.viewModel?.getList()?.observe(this, Observer { pagedList ->
            (mBinding.rvPagedlist.adapter as KmPagedlistAdapter).setList(pagedList as PagedList<Any>)
        })
    }

    private fun initAdapter() {
        kmPagedListAdapter = KmBuilder.getPagedListAdapter()
            .createBinding { parent, viewType ->
                val mBinding: ItemAdapterBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(this@PagedlistAdapterSampleActivity),
                    R.layout.item_adapter,
                    parent,
                    false
                )
                mBinding.viewModel = ItemViewModel(this@PagedlistAdapterSampleActivity.application)
                mBinding.viewModel?.initListener(this)
                mBinding
            }
            .bindItem { binding, model ->
                (binding as ItemAdapterBinding).viewModel?.setModel(model as TestModel)
            }
            .setDiffCallback { KmPagedlistAdapter.Companion.KmDiffItemCallback() }
            .build()
        mBinding.rvPagedlist.adapter = kmPagedListAdapter
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_pagedlist_sample)
        mBinding.viewModel = ViewModelProviders.of(this).get(PagedlistAdapterSampleViewModel::class.java)
    }

    override fun onClickListener(item: Any?, type: Int) {
        Toast.makeText(this, "${(item as TestModel).name} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClickListener(item: Any?, type: Int) {
        Toast.makeText(this, "${(item as TestModel).name} long clicked", Toast.LENGTH_SHORT).show()
    }
}