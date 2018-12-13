package com.kodmap.app.library.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

open class KmViewHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)