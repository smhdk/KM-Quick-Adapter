package com.kodmap.app.library

import com.kodmap.app.library.builder.KmPagedlistAdapterBuilder
import com.kodmap.app.library.builder.KmRvAdapterBuilder

object KmBuilder {

    fun getPagedListAdapter(): KmPagedlistAdapterBuilder =
        KmPagedlistAdapterBuilder()

    fun getRvAdapter(): KmRvAdapterBuilder =
        KmRvAdapterBuilder()

}