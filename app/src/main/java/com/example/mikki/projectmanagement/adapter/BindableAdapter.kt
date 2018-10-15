package com.example.mikki.projectmanagement.adapter

interface BindableAdapter<T> {
    fun setData(items: List<T>)
    fun changedPositions(positions: Int)
}