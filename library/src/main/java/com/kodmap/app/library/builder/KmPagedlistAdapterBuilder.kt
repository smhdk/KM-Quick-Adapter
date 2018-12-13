package com.kodmap.app.library.builder

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kodmap.app.library.adapter.pagedlistAdapter.KmPagedlistAdapter

class KmPagedlistAdapterBuilder {
    private var diffCallback: (() -> DiffUtil.ItemCallback<Any>)? = null

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

    fun setDiffCallback(diffCallback: () -> DiffUtil.ItemCallback<Any>): KmPagedlistAdapterBuilder {
        this.diffCallback = diffCallback
        return this
    }

    fun createBinding(binding: (parent: ViewGroup, viewType: Int) -> ViewDataBinding): KmPagedlistAdapterBuilder {
        this.viewDataBinding = binding
        return this
    }

    fun itemCount(getItemsCount: (() -> Int)): KmPagedlistAdapterBuilder {
        this.getItemCount = getItemsCount
        return this
    }

    fun itemCount(count: Int): KmPagedlistAdapterBuilder {
        this.getItemCount = { count }
        return this
    }

    fun bindItem(bindItem: (binding: ViewDataBinding, item: Any) -> Unit): KmPagedlistAdapterBuilder {
        this.bind = bindItem
        return this
    }

    fun getItemId(getItemId: (model: Any) -> Long): KmPagedlistAdapterBuilder {
        this.getItemId = getItemId
        return this
    }

    fun getItemViewType(getItemViewType: (model: Any) -> Int): KmPagedlistAdapterBuilder {
        this.getItemViewType = getItemViewType
        return this
    }

    fun setHasStableIds(setHasStableIds: () -> Unit): KmPagedlistAdapterBuilder {
        this.setHasStableIds = setHasStableIds
        return this
    }

    fun onAttachedToRecyclerView(onAttachedToRecyclerView: (recyclerView: RecyclerView) -> Unit): KmPagedlistAdapterBuilder {
        this.onAttachedToRecyclerView = onAttachedToRecyclerView
        return this
    }

    fun onDetachedFromRecyclerView(onDetachedFromRecyclerView: (recyclerView: RecyclerView) -> Unit): KmPagedlistAdapterBuilder {
        this.onDetachedFromRecyclerView = onDetachedFromRecyclerView
        return this
    }

    fun onViewAttachedToWindow(onViewAttachedToWindow: (holder: RecyclerView.ViewHolder) -> Unit): KmPagedlistAdapterBuilder {
        this.onViewAttachedToWindow = onViewAttachedToWindow
        return this
    }

    fun onViewDetachedFromWindow(onViewDetachedFromWindow: (holder: RecyclerView.ViewHolder) -> Unit): KmPagedlistAdapterBuilder {
        this.onViewDetachedFromWindow = onViewDetachedFromWindow
        return this
    }

    fun onFailedToRecycleView(onFailedToRecycleView: (holder: RecyclerView.ViewHolder) -> Boolean): KmPagedlistAdapterBuilder {
        this.onFailedToRecycleView = onFailedToRecycleView
        return this
    }

    fun onViewRecycled(onViewRecycled: (holder: RecyclerView.ViewHolder) -> Unit): KmPagedlistAdapterBuilder {
        this.onViewRecycled = onViewRecycled
        return this
    }

    fun registerAdapterDataObserver(registerAdapterDataObserver: (observer: RecyclerView.AdapterDataObserver) -> Unit): KmPagedlistAdapterBuilder {
        this.registerAdapterDataObserver = registerAdapterDataObserver
        return this
    }

    fun unregisterAdapterDataObserver(unregisterAdapterDataObserver: (observer: RecyclerView.AdapterDataObserver) -> Unit): KmPagedlistAdapterBuilder {
        this.unregisterAdapterDataObserver = unregisterAdapterDataObserver
        return this
    }

    fun build(): KmPagedlistAdapter {
        if (viewDataBinding == null) throw NullPointerException("ViewDataBinding must not be null.")
        if (diffCallback == null) throw NullPointerException("DiffCallback must not be null.")

        return KmPagedlistAdapter(
            mGetDiffCallback = diffCallback!!,
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