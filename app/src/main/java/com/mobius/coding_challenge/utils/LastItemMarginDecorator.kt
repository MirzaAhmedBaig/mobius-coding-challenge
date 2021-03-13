package com.mobius.coding_challenge.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mobius.coding_challenge.extensions.dpToPx

public class LastItemMarginDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount ?: 0) - 1) {
            outRect.bottom = 16.dpToPx()
        }
    }
}