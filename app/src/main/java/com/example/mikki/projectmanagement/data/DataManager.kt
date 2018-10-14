package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper

class DataManager:IDataManager {
    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        iNetworkHelper.storeNewSubTaskToServer(subTask)
    }

    override fun getProjectList() {
        iNetworkHelper.getProjectList()
    }

    override fun storeNewProjectToServer(p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(p)
    }


    companion object {
        val iNetworkHelper: INetworkHelper = NetworkHelper()
    }

}