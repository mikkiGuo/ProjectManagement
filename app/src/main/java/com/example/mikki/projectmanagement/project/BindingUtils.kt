package com.example.mikki.projectmanagement.project

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<T>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(items)
    }
}

@BindingAdapter("changedPositions")
fun <T> setDataChanged(recyclerView: RecyclerView, positions: Int) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).changedPositions(positions)
    }
}