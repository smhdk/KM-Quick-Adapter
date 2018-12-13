package com.kodmap.app.library.adapter.pagedlistAdapter

import android.arch.paging.PagedList
import android.arch.paging.PagedListAdapter
import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kodmap.app.library.adapter.KmViewHolder

class KmPagedlistAdapter(
    private val mCreateBinding: ((parent: ViewGroup, viewType: Int) -> ViewDataBinding),
    private val mBind: (binding: ViewDataBinding, item: Any) -> Unit,
    private val mGetItemCount: (() -> Int)?,
    private val mGetItemId: ((model: Any) -> Long)?,
    private val mGetItemViewType: ((model: Any) -> Int)?,
    private val mGetHasStableIds: (() -> Unit)?,
    private val mOnAttachedToRecyclerView: ((recyclerView: RecyclerView) -> Unit)?,
    private val mOnDetachedFromRecyclerView: ((recyclerView: RecyclerView) -> Unit)?,
    private val mOnViewAttachedToWindow: ((holder: RecyclerView.ViewHolder) -> Unit)?,
    private val mOnViewDetachedFromWindow: ((holder: RecyclerView.ViewHolder) -> Unit)?,
    private val mOnFailedToRecycleView: ((holder: RecyclerView.ViewHolder) -> Boolean)?,
    private val mOnViewRecycled: ((holder: RecyclerView.ViewHolder) -> Unit)?,
    private val mRegisterAdapterDataObserver: ((observer: RecyclerView.AdapterDataObserver) -> Unit)?,
    private val mUnregisterAdapterDataObserver: ((observer: RecyclerView.AdapterDataObserver) -> Unit)?,
    private val mGetDiffCallback: (() -> DiffUtil.ItemCallback<Any>)
) : PagedListAdapter<Any, RecyclerView.ViewHolder>(mGetDiffCallback()) {

    fun setList(list: PagedList<Any>) {
        submitList(list)
    }

    private fun bindItem(binding: ViewDataBinding, item: Any) =
        mBind.invoke(binding, item)

    override fun getItemCount(): Int {
        when {
            mGetItemCount != null -> return mGetItemCount.invoke()
            currentList != null -> return currentList!!.size
            else -> return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getViewHolder(parent, viewType)

    private fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        KmViewHolder(createBinding(parent, viewType))

    private fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        mCreateBinding.invoke(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        bindItem((holder as KmViewHolder<*>).binding, getItem(position)!!)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        bindItem((holder as KmViewHolder<*>).binding, getItem(position)!!)
    }

    override fun getItemId(position: Int): Long =
        mGetItemId?.invoke(getItem(position)!!) ?: super.getItemId(position)

    override fun getItemViewType(position: Int): Int =
        mGetItemViewType?.invoke(getItem(position)!!) ?: super.getItemViewType(position)

    override fun setHasStableIds(hasStableIds: Boolean): Unit =
        mGetHasStableIds?.invoke() ?: super.setHasStableIds(hasStableIds)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) =
        mOnAttachedToRecyclerView?.invoke(recyclerView) ?: super.onAttachedToRecyclerView(recyclerView)

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) =
        mOnDetachedFromRecyclerView?.invoke(recyclerView) ?: super.onDetachedFromRecyclerView(recyclerView)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        mOnViewAttachedToWindow?.invoke(holder) ?: super.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        mOnViewDetachedFromWindow?.invoke(holder) ?: super.onViewDetachedFromWindow(holder)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        mOnFailedToRecycleView?.invoke(holder) ?: super.onFailedToRecycleView(holder)

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        mOnViewRecycled?.invoke(holder) ?: super.onViewRecycled(holder)

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) =
        mRegisterAdapterDataObserver?.invoke(observer) ?: super.registerAdapterDataObserver(observer)

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) =
        mUnregisterAdapterDataObserver?.invoke(observer) ?: super.unregisterAdapterDataObserver(observer)

    companion object {
        open class KmDiffItemCallback : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Any, newItem: Any) =
                oldItem == newItem
        }
    }
}