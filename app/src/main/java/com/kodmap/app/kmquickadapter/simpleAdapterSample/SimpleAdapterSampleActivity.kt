package com.kodmap.app.kmquickadapter.simpleAdapterSample

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.Toast
import com.kodmap.app.kmquickadapter.R
import com.kodmap.app.kmquickadapter.databinding.ActivitySimpleListBinding
import com.kodmap.app.kmquickadapter.databinding.ItemAdapterBinding
import com.kodmap.app.kmquickadapter.model.ItemViewModel
import com.kodmap.app.kmquickadapter.model.TestModel
import com.kodmap.app.library.adapter.KmDiffCallback
import com.kodmap.app.library.adapter.rvAdapter.KmRvAdapter
import com.kodmap.app.library.KmBuilder
import com.kodmap.app.library.listener.AdapterCallback

class SimpleAdapterSampleActivity() : AppCompatActivity(), AdapterCallback {

    private lateinit var mBinding: ActivitySimpleListBinding
    private lateinit var kmAdapter: KmRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_list)

        initBinding()

        initAdapter()

        setList()
    }

    private fun setList() {
        (mBinding.rvSimpleList.adapter as KmRvAdapter).setList(mBinding.viewModel?.getList()!!)
    }

    private fun initAdapter() {
        kmAdapter = KmBuilder.getRvAdapter()
            .createBinding { parent, viewType ->
                val mBinding: ItemAdapterBinding = inflate(
                    LayoutInflater.from(this@SimpleAdapterSampleActivity),
                    R.layout.item_adapter,
                    parent,
                    false
                )
                mBinding.viewModel = ItemViewModel(this@SimpleAdapterSampleActivity.application)
                mBinding.viewModel?.initListener(this)
                mBinding
            }
            .bindItem { binding, model ->
                (binding as ItemAdapterBinding).viewModel?.setModel(model as TestModel)
                binding.executePendingBindings()
            }
            .setDiffCallback { newList, oldList -> KmDiffCallback(newList, oldList) }
            .getItemId { model -> (model as TestModel).name.hashCode().toLong() }
            .build()
        mBinding.rvSimpleList.adapter = kmAdapter
    }

    private fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_simple_list)
        mBinding.viewModel = ViewModelProviders.of(this).get(SimpleAdapterSampleViewModel::class.java)
    }

    override fun onClickListener(item: Any?, type: Int) {
        Toast.makeText(this, "${(item as TestModel).name} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onLongClickListener(item: Any?, type: Int) {
        Toast.makeText(this, "${(item as TestModel).name} long clicked", Toast.LENGTH_SHORT).show()
    }
}