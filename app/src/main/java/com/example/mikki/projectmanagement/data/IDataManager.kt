package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

    interface OnAdminCreateTaskListener {
        fun createTask(string: String)
    }

    interface OnAdminTaskListListener {
        fun getAdminTaskList(taskList: ArrayList<TaskItem>?)
    }

    interface OnAdminTaskUpdatedListener {
        fun updateTask(s: String)
    }

}