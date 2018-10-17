package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

interface INetworkHelper {

    fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel)
    fun getProjectList(viewModel: ProjectViewModel)

    fun createTask(viewModel: TaskViewModel, listener: OnAdminCreateTaskListener, taskItem: TaskItem)
    fun getAdminTaskList(viewModel: TaskViewModel, listener: OnAdminTaskListListener)
    fun getUserTaskList(viewModel: TaskViewModel, id: String)
    fun updateTaskDetails(viewModel: TaskViewModel, listener: OnAdminTaskUpdatedListener, taskItem: TaskItem)
    fun getTeamMemberByTask(viewModel: TaskViewModel, listener: OnTaskMemberListener, taskItem: TaskItem)
    fun getMemberDetails(viewModel: TaskViewModel, addlistener: OnAddMemberDetailsListener, memberListListener: OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?)

    fun updateProject(p:ProjectsItem, viewModel: ProjectViewModel, index:Int)

    fun createTask(listener: OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem)
    fun getAdminTaskList(listener: OnAdminTaskListListener)
    fun getUserTaskList(id: String)
    fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)

    /******************************************************************
     * Team Stuff
     ******************************************************************/
    fun createTeamForProject(projectId: Int,
                             team_member_userid: Int,
                             index:Int,
                             viewModel: TeamViewModel)

    fun getEmployeeList(viewModel: TeamViewModel)

}