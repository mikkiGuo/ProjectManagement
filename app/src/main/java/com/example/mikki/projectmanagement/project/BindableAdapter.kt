package com.example.mikki.projectmanagement.project

interface BindableAdapter<T> {
    fun setData(items: List<T>)
    fun changedPositions(positions: Int)
}