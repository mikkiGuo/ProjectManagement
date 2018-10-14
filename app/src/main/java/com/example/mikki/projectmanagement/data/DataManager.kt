package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper

class DataManager:IDataManager {

    override fun getProjectList() {
        iNetworkHelper.getProjectList()
    }

    override fun storeNewProjectToServer(p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(p)
    }

    override fun createTask(taskItem: ProjectTaskItem) {
        iNetworkHelper.createTask(taskItem)
    }

    override fun getTaskList() {
        iNetworkHelper.getTaskList()
    }

    companion object {
        val iNetworkHelper = NetworkHelper()
    }

}