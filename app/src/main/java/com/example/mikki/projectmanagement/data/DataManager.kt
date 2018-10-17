package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel

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

    override fun createTask(viewModel: TaskViewModel, listener: IDataManager.OnAdminCreateTaskListener, taskItem: TaskItem) {
        iNetworkHelper.createTask(viewModel, listener, taskItem)
    }

    override fun getAdminTaskList(viewModel: TaskViewModel, listener: IDataManager.OnAdminTaskListListener) {
        return iNetworkHelper.getAdminTaskList(viewModel, listener)
    }

    override fun getUserTaskList(viewModel: TaskViewModel, id: String) {
        iNetworkHelper.getUserTaskList(viewModel, id)
    }

    override fun updateTaskDetails(viewModel: TaskViewModel, listener: IDataManager.OnAdminTaskUpdatedListener, taskItem: TaskItem) {
        iNetworkHelper.updateTaskDetails(viewModel, listener, taskItem)
    }

    override fun getTeamMemberByTask(viewModel: TaskViewModel, listener: IDataManager.OnTaskMemberListener, taskItem: TaskItem) {
        iNetworkHelper.getTeamMemberByTask(viewModel, listener, taskItem)
    }

    override fun getMemberDetails(viewModel: TaskViewModel,
                                  addlistener: IDataManager.OnAddMemberDetailsListener,
                                  memberListListener: IDataManager.OnTaskMemberListener,
                                  memberList: ArrayList<TaskMemberItem>?) {
        iNetworkHelper.getMemberDetails(viewModel, addlistener, memberListListener, memberList)
    }

    companion object {
        val iNetworkHelper = NetworkHelper()
    }

}