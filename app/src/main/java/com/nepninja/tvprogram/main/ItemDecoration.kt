package com.nepninja.tvprogram.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class ItemOffsetDecoration : RecyclerView.ItemDecoration {
    private val _itemOffset: Int

    constructor(itemOffset: Int) {
        _itemOffset = itemOffset
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(_itemOffset, _itemOffset, _itemOffset, _itemOffset)
    }
}