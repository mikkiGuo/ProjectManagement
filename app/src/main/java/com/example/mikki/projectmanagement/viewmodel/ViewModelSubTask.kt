package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem

class ViewModelSubTask : BaseObservable() {

    val dataManager:IDataManager = DataManager()

    @get:Bindable
    var subTaskList: MutableList<ProjectSubTaskItem> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.subTaskList)
        }

    @get:Bindable
    var changedPositions: Int = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.changedPositions)
        }

    fun initList() {
        dataManager.getSubTasksList(this)
    }

    fun upadteSubTaskList(subTaskItem: ProjectSubTaskItem) {
        subTaskList.add(subTaskItem)
        changedPositions = 5
    }
}