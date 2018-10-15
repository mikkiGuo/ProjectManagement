package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.project.ProjectViewModel

class DataManager:IDataManager {
    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        iNetworkHelper.storeNewSubTaskToServer(subTask)
    }

    override fun getProjectList(viewModel: ProjectViewModel) {
        iNetworkHelper.getProjectList(viewModel)
    }

    override fun storeNewProjectToServer(p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(p)
    }


    companion object {
        val iNetworkHelper: INetworkHelper = NetworkHelper()
    }

}