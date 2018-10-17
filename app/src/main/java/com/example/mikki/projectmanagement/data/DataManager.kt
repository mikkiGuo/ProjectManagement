package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

class DataManager:IDataManager {


    override fun viewTeamMemberBySubTask(listener: IDataManager.OnAdminViewTeamMemeberBySubTask,
                                         subTask: ProjectSubTaskItem) {
        iNetworkHelper.viewTeamMemberBySubTask(listener, subTask)
    }


    override fun assignSubTaskToUser(listner: IDataManager.OnAdminAssignSubTaskToUserListener,
                                     subTask: ProjectSubTaskItem) {
        iNetworkHelper.assignSubTaskToUser(listner, subTask)
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

    override fun viewSubTaskDetailByUser(listner: IDataManager.OnUserAdminViewSubTaskDetailListener,
                                         subTask: ProjectSubTaskItem) {
        iNetworkHelper.viewSubTaskDetailByUser(listner, subTask)
    }

    override fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.createNewSubTask(listener, subTask)
    }

    override fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                                  subTask: ProjectSubTaskItem) {
        iNetworkHelper.editSubTask(listener, subTask)

    }

    override fun editSubTaskStatus(listner: IDataManager.OnUserEditSubTaskStatusListener, subTask: ProjectSubTaskItem) {
        iNetworkHelper.editSubTaskStatus(listner, subTask)
    }

//    override fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem) {
//        iNetworkHelper.storeNewSubTaskToServer(subTask)
//    }

    override fun getProjectList(viewModel: ProjectViewModel) {
        iNetworkHelper.getProjectList(viewModel)
    }

    override fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel) {
        iNetworkHelper.storeNewProjectToServer(p, viewModel)
    }

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask) {
        iNetworkHelper.getSubTasksList(subTaskViewModel)
    }

    /******************************************************************
     * Task Stuff Divider
     ******************************************************************/
    override fun createTask(listener: IDataManager.OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem) {
        iNetworkHelper.createTask(listener, adminTaskItem)
    }

    override fun getAdminTaskList(listener: IDataManager.OnAdminTaskListListener) {
        return iNetworkHelper.getAdminTaskList(listener)
    }

    override fun getUserTaskList(id: String) {
        iNetworkHelper.getUserTaskList(id)
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