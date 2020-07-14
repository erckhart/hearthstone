package com.voidx.hearthstone.view.widget.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

data class MarginItemDecorator(
    private val top: Int = 0,
    private val right: Int = 0,
    private val bottom: Int = 0,
    private val left: Int = 0
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = top
        outRect.right = right
        outRect.bottom = bottom
        outRect.left = left
    }

}