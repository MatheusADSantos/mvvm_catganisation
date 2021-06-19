package com.schaefer.core.extension

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.layoutManagerFactory(columnCount: Int) = when {
    columnCount <= 1 -> LinearLayoutManager(context)
    else -> GridLayoutManager(context, columnCount)
}
