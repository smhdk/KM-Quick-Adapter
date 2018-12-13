package com.kodmap.app.library.listener

interface AdapterCallback {
    fun onClickListener(item: Any?, type: Int)
    fun onLongClickListener(item: Any?, type: Int)
}