package com.kodmap.app.library.builder

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kodmap.app.library.adapter.rvAdapter.KmRvAdapter

class KmRvAdapterBuilder {

    private var diffCallback: ((newList: List<Any>, oldList: List<Any>) -> DiffUtil.Callback)? = null

    private var viewDataBinding: ((parent: ViewGroup, viewType: Int) -> ViewDataBinding)? = null

    private var bind: ((binding: ViewDataBinding, item: Any) -> Unit)? = null

    private var getItemCount: (() -> Int)? = null

    private var getItemId: ((model: Any) -> Long)? = null

    private var getItemViewType: ((model: Any) -> Int)? = null

    private var setHasStableIds: (() -> Unit)? = null

    private var onAttachedToRecyclerView: ((recyclerView: RecyclerView) -> Unit)? = null

    private var onDetachedFromRecyclerView: ((recyclerView: RecyclerView) -> Unit)? = null

    private var onViewAttachedToWindow: ((holder: RecyclerView.ViewHolder) -> Unit)? = null

    private var onViewDetachedFromWindow: ((holder: RecyclerView.ViewHolder) -> Unit)? = null

    private var onFailedToRecycleView: ((holder: RecyclerView.ViewHolder) -> Boolean)? = null

    private var onViewRecycled: ((holder: RecyclerView.ViewHolder) -> Unit)? = null

    private var registerAdapterDataObserver: ((observer: RecyclerView.AdapterDataObserver) -> Unit)? = null

    private var unregisterAdapterDataObserver: ((observer: RecyclerView.AdapterDataObserver) -> Unit)? = null

    fun setDiffCallback(diffCallback: (newList: List<Any>, oldList: List<Any>) -> DiffUtil.Callback): KmRvAdapterBuilder {
        this.diffCallback = diffCallback
        return this
    }

    fun createBinding(binding: (parent: ViewGroup, viewType: Int) -> ViewDataBinding): KmRvAdapterBuilder {
        this.viewDataBinding = binding
        return this
    }

    fun itemCount(getItemsCount: (() -> Int)): KmRvAdapterBuilder {
        this.getItemCount = getItemsCount
        return this
    }

    fun itemCount(count: Int): KmRvAdapterBuilder {
        this.getItemCount = { count }
        return this
    }

    fun bindItem(bindItem: (binding: ViewDataBinding, item: Any) -> Unit): KmRvAdapterBuilder {
        this.bind = bindItem
        return this
    }

    fun getItemId(getItemId: (model: Any) -> Long): KmRvAdapterBuilder {
        this.getItemId = getItemId
        return this
    }

    fun getItemViewType(getItemViewType: (model: Any) -> Int): KmRvAdapterBuilder {
        this.getItemViewType = getItemViewType
        return this
    }

    fun setHasStableIds(setHasStableIds: () -> Unit): KmRvAdapterBuilder {
        this.setHasStableIds = setHasStableIds
        return this
    }

    fun onAttachedToRecyclerView(onAttachedToRecyclerView: (recyclerView: RecyclerView) -> Unit): KmRvAdapterBuilder {
        this.onAttachedToRecyclerView = onAttachedToRecyclerView
        return this
    }

    fun onDetachedFromRecyclerView(onDetachedFromRecyclerView: (recyclerView: RecyclerView) -> Unit): KmRvAdapterBuilder {
        this.onDetachedFromRecyclerView = onDetachedFromRecyclerView
        return this
    }

    fun onViewAttachedToWindow(onViewAttachedToWindow: (holder: RecyclerView.ViewHolder) -> Unit): KmRvAdapterBuilder {
        this.onViewAttachedToWindow = onViewAttachedToWindow
        return this
    }

    fun onViewDetachedFromWindow(onViewDetachedFromWindow: (holder: RecyclerView.ViewHolder) -> Unit): KmRvAdapterBuilder {
        this.onViewDetachedFromWindow = onViewDetachedFromWindow
        return this
    }

    fun onFailedToRecycleView(onFailedToRecycleView: (holder: RecyclerView.ViewHolder) -> Boolean): KmRvAdapterBuilder {
        this.onFailedToRecycleView = onFailedToRecycleView
        return this
    }

    fun onViewRecycled(onViewRecycled: (holder: RecyclerView.ViewHolder) -> Unit): KmRvAdapterBuilder {
        this.onViewRecycled = onViewRecycled
        return this
    }

    fun registerAdapterDataObserver(registerAdapterDataObserver: (observer: RecyclerView.AdapterDataObserver) -> Unit): KmRvAdapterBuilder {
        this.registerAdapterDataObserver = registerAdapterDataObserver
        return this
    }

    fun unregisterAdapterDataObserver(unregisterAdapterDataObserver: (observer: RecyclerView.AdapterDataObserver) -> Unit): KmRvAdapterBuilder {
        this.unregisterAdapterDataObserver = unregisterAdapterDataObserver
        return this
    }

    fun build(): KmRvAdapter {
        if (viewDataBinding == null) throw NullPointerException("ViewDataBinding must not be null.")

        return KmRvAdapter(
            mGetDiffCallback = diffCallback,
            mBind = bind!!,
            mGetItemCount = getItemCount,
            mCreateBinding = viewDataBinding!!,
            mGetItemId = getItemId,
            mGetItemViewType = getItemViewType,
            mGetHasStableIds = setHasStableIds,
            mOnAttachedToRecyclerView = onAttachedToRecyclerView,
            mOnDetachedFromRecyclerView = onDetachedFromRecyclerView,
            mOnViewAttachedToWindow = onViewAttachedToWindow,
            mOnViewDetachedFromWindow = onViewDetachedFromWindow,
            mOnFailedToRecycleView = onFailedToRecycleView,
            mOnViewRecycled = onViewRecycled,
            mRegisterAdapterDataObserver = registerAdapterDataObserver,
            mUnregisterAdapterDataObserver = unregisterAdapterDataObserver
        )
    }
}