package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel

interface INetworkHelper {

    fun storeNewProjectToServer(p: ProjectsItem)

    fun getProjectList()

    fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener,
                         subTask:ProjectSubTaskItem)

    fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                         subTask:ProjectSubTaskItem)

    fun getSubTasksList(subTaskViewModel: ViewModelSubTask)
    fun getProjectList(viewModel: ProjectViewModel)
    fun createTask(listener: OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem)
    fun getAdminTaskList(listener: OnAdminTaskListListener)
    fun getUserTaskList(id: String)
    fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)
}