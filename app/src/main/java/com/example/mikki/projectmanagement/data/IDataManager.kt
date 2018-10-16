package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.database.IDbHelper
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

    interface OnAdminCreateSubTaskListener {
        fun createTask(message: String)
    }

    interface OnAdminEditSubTaskListener {
        fun editTask(message: String)
    }

    interface OnAdminCreateTaskListener {
        fun createTask()
    }

    interface OnAdminTaskListListener {
        fun getAdminTaskList(adminTaskList: ArrayList<ProjectAdminTaskItem>?)
    }

}