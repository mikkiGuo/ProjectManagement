package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.subtaskmodel.ViewsubtasksItem

class ViewModelSubTask : BaseObservable(), IDataManager.OnUserAdminViewSubTaskDetailListener {


    override fun viewSubTaskByDetailByUser(subTask: ProjectSubTaskItem) {

    }

    val dataManager:IDataManager = DataManager()

    @get:Bindable
    var subTaskList: MutableList<ProjectSubTaskItem> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.subTaskList)
        }

    @get:Bindable
    var subTaskListByUser: MutableList<ViewsubtasksItem> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.subTaskListByUser)
        }

    @get:Bindable
    var changedPositions: Int = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.changedPositions)
        }

    fun initList() {
        dataManager.getSubTasksList(this)
        dataManager.viewSubTaskListByUser(this, "3", "3")
    }

    fun upadteSubTaskList(subTaskItem: ProjectSubTaskItem) {
        subTaskList.add(subTaskItem)
        changedPositions = 0
    }

    fun upadteSubTaskListByUser(viewsubtasksItem: ViewsubtasksItem) {
        subTaskListByUser.add(viewsubtasksItem)
        changedPositions = 0
    }
}