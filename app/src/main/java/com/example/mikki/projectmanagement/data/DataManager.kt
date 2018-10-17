package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

class DataManager:IDataManager {

    companion object {
        val iNetworkHelper = NetworkHelper()
    }

    override fun updateProject(p: ProjectsItem,
                               viewModel: ProjectViewModel, index:Int) {
        iNetworkHelper.updateProject(p, viewModel, index)
    }

    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
        iNetworkHelper.storeNewSubTaskToServer(subTask)
    }

    override fun getProjectList(viewModel: ProjectViewModel) {
        iNetworkHelper.getProjectList(viewModel)
    }

    override fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel) {
        iNetworkHelper.storeNewProjectToServer(p, viewModel)
    }

    override fun createTask(viewModel: TaskViewModel, listener: IDataManager.OnAdminCreateTaskListener, taskItem: TaskItem) {
        iNetworkHelper.createTask(viewModel, listener, taskItem)
    }

    override fun getAdminTaskList(viewModel: TaskViewModel, listener: IDataManager.OnAdminTaskListListener, projectId: Int) {
        return iNetworkHelper.getAdminTaskList(viewModel, listener, projectId)
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

    /******************************************************************
     * Team Stuff Divider
     ******************************************************************/
    override fun createTeamForProject(projectId: Int,
                                      team_member_userid: Int,
                                      index: Int,
                                      viewModel: TeamViewModel) {
        iNetworkHelper.createTeamForProject(projectId, team_member_userid,
                index, viewModel)
    }

    override fun getEmployeeList(viewModel: TeamViewModel) {
        iNetworkHelper.getEmployeeList(viewModel)
    }



}