package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel

interface INetworkHelper {

    fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel)
    fun getProjectList(viewModel: ProjectViewModel)
    fun updateProject(p:ProjectsItem, viewModel: ProjectViewModel, index:Int)

    fun createTask(listener: OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem)
    fun getAdminTaskList(listener: OnAdminTaskListListener)
    fun getUserTaskList(id: String)
    fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)

    /******************************************************************
     * Team Stuff
     ******************************************************************/
    fun createTeamForProject(projectId: Int, team_member_userid: Int)

}