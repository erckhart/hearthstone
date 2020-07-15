package com.voidx.hearthstone.view.widget.recyclerview.binding

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindableViewHolder<VDB : ViewDataBinding>(val viewDataBinding: VDB) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    init {
        viewDataBinding.executePendingBindings()
    }

}

fun <VDB: ViewDataBinding> BindableViewHolder<VDB>.binding(): VDB {
    return this.viewDataBinding
}