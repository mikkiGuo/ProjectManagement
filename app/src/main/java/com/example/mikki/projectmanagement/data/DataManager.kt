package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel

class DataManager:IDataManager {

    override fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.createNewSubTask(listener, subTask)
    }

    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.editSubTask(listener, subTask)

    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        iNetworkHelper.storeNewSubTaskToServer(subTask)
    }

    override fun getProjectList(viewModel: ProjectViewModel) {
        iNetworkHelper.getProjectList(viewModel)
    }

    override fun storeNewProjectToServer(p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(p)
    }

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask) {
        iNetworkHelper.getSubTasksList(subTaskViewModel)
    }

    override fun createTask(listener: IDataManager.OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem) {
        iNetworkHelper.createTask(listener, adminTaskItem)
    }

    override fun getAdminTaskList(listener: IDataManager.OnAdminTaskListListener) {
        return iNetworkHelper.getAdminTaskList(listener)
    }

    override fun getUserTaskList(id: String) {
        iNetworkHelper.getUserTaskList(id)
    }

    companion object {
        val iNetworkHelper = NetworkHelper()
    }

}