package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.coroutines.experimental.newCoroutineContext

class DataManager:IDataManager {
    override fun login(listener: IDataManager.OnLoginListener, loginInfo: LoginInfo) {
        iNetworkHelper.login(listener, loginInfo)
    }

    companion object {
        val iNetworkHelper = NetworkHelper()
    }

    override fun register(listener: IDataManager.OnRegisterListener, register: Register) {
        iNetworkHelper.register(listener, register)
    }

    /******************************************************************
     * Project Stuff Divider
     ******************************************************************/

    override fun storeNewProjectToServer(listener:IDataManager.OnCreateProjectListener,p: ProjectsItem) {
        iNetworkHelper.storeNewProjectToServer(listener, p)
    }

    override fun updateProject(listener: IDataManager.OnProjectListListener,
                               p: ProjectsItem,
                               index: Int) {
        iNetworkHelper.updateProject(listener, p, index)
    }

    override fun getProjectList(listener: IDataManager.OnProjectListListener) {
        iNetworkHelper.getProjectList(listener)
    }

    /******************************************************************
     * Task Stuff Divider
     ******************************************************************/

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
                                  listener: IDataManager.OnAddMemberDetailsListener,
                                  memberListListener: IDataManager.OnTaskMemberListener,
                                  memberList: ArrayList<TaskMemberItem>?) {
        iNetworkHelper.getMemberDetails(viewModel, listener, memberListListener, memberList)
    }

    override fun assignMemberToTask(viewModel: TaskViewModel, listener: IDataManager.OnAssignMemberListener, memberItem: TaskMemberItem) {
        iNetworkHelper.assignMemberToTask(viewModel, listener, memberItem)
    }

    /******************************************************************
     * SubTask Stuff Divider
     ******************************************************************/

    override fun viewTeamMemberBySubTask(listener: IDataManager.OnAdminViewTeamMemeberBySubTask,
                                         subTask: ProjectSubTaskItem) {
        iNetworkHelper.viewTeamMemberBySubTask(listener, subTask)
    }

    override fun assignSubTaskToUser(listner: IDataManager.OnAdminAssignSubTaskToUserListener,
                                     subTask: ProjectSubTaskItem, userId: Int, position: Int) {
        iNetworkHelper.assignSubTaskToUser(listner, subTask, userId, position)

    }

    override fun viewSubTaskDetailByUser(listner: IDataManager.OnUserAdminViewSubTaskDetailListener,
                                         subTask: ProjectSubTaskItem) {
        iNetworkHelper.viewSubTaskDetailByUser(listner, subTask)
    }

    override fun viewSubTaskListByUser(subTaskViewModel: ViewModelSubTask, userId: String, taskId: String) {
        iNetworkHelper.viewSubTaskListByUser(subTaskViewModel, userId, taskId)
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

    override fun getSubTasksList(subTaskViewModel: ViewModelSubTask, taskId: String) {
        iNetworkHelper.getSubTasksList(subTaskViewModel, taskId)
    }

    override fun getTeamMemberBySubTask(viewModelSubTask: ViewModelSubTask, listener: IDataManager.OnTaskMemberListener, subTaskItem: ProjectSubTaskItem) {
        iNetworkHelper.getTeamMemberBySubTask(viewModelSubTask, listener, subTaskItem)
    }

    override fun getMemberDetailsSubTask(viewModelSubTask: ViewModelSubTask,
                                         addlistener: IDataManager.OnAddMemberDetailsListener, memberListListener: IDataManager.OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?) {
        iNetworkHelper.getMemberDetailsSubTask(viewModelSubTask, addlistener,memberListListener, memberList)
    }

    /******************************************************************
     * Team Stuff Divider
     ******************************************************************/
    override fun createTeamForProject(listener:IDataManager.OnCreateTeamForProject,
                                      projectId: Int,
                                      team_member_userid: Int,
                                      index: Int) {
        iNetworkHelper.createTeamForProject(listener,
                projectId,
                team_member_userid,
                index)
    }

    override fun getEmployeeList(listener:IDataManager.OnCreateTeamForProject) {
        iNetworkHelper.getEmployeeList(listener)
    }

    override fun getProjectTeamList(listener: IDataManager.OnDisplayProjectTeam,
                                    projectId: Int) {
        iNetworkHelper.getProjectTeamList(listener,projectId)
    }

    override fun getMemberDetailForProjectTeam(listener: IDataManager.OnDisplayProjectTeam,
                                               memberId: String) {
        iNetworkHelper.getMemberDetailForProjectTeam(listener, memberId)
    }

}