package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask

class DataManager:IDataManager {

    override fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.createNewSubTask(listener, subTask)
    }

    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.editSubTask(listener, subTask)
    }

    override fun getProjectList() {
        iNetworkHelper.getProjectList()
    }

    override fun storeNewProjectToServer(p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(p)
    }

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask) {
        iNetworkHelper.getSubTasksList(subTaskViewModel)
    }


    companion object {
        val iNetworkHelper: INetworkHelper = NetworkHelper()
    }

}