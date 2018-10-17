package com.example.mikki.projectmanagement.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.view.task.TaskRecyclerAdapter
import kotlin.collections.ArrayList

class TaskViewModel(val context: Context): BaseObservable() {

    val iDataManager = DataManager()
    var adapter = TaskRecyclerAdapter(context, this)

    @get:Bindable var taskList = ArrayList<TaskItem>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.taskList)
        }

    @get:Bindable var taskItem = TaskItem()
        set(value) {
            field = value
            notifyPropertyChanged(BR.taskItem)
        }

    fun createTask(listener: OnAdminCreateTaskListener, taskItem: TaskItem) {
        iDataManager.createTask(this, listener, taskItem)
    }

    fun isTaskCreated(listener: OnAdminCreateTaskListener, b: Boolean) {
        var s: String
        if (b) s = "Task Successfully Created" else s = "Project Not Found"
        listener.createTask(s)
    }

    fun createTaskList(listener: OnAdminTaskListListener) {
        iDataManager.getAdminTaskList(this, listener)
        Log.d("ninntag", "viewmodel createtasklist: " + taskList.toString())
    }

    fun showTaskList(listener: OnAdminTaskListListener, taskList: ArrayList<TaskItem>?) {
        this.taskList = taskList!!
        listener.getAdminTaskList(this.taskList)
        Log.d("ninntag", "viewmodel showtasklist: " + this.taskList.toString())
    }

    fun updateTaskDetails(listener: OnAdminTaskUpdatedListener, taskItem: TaskItem) {
        iDataManager.updateTaskDetails(this, listener, taskItem)
    }

    fun isTaskUpdated(listener: OnAdminTaskUpdatedListener, b: Boolean) {
        var s: String
        if (b) s = "Task Details Updated" else s = "Project Not Found"
        listener.updateTask(s)
    }
}