package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

interface INetworkHelper {

    fun register(listener: OnRegisterListener, register: Register)

    fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener, subTask:ProjectSubTaskItem)
    fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener, subTask:ProjectSubTaskItem)
    fun editSubTaskStatus(listner: OnUserEditSubTaskStatusListener, subTask: ProjectSubTaskItem)
    fun assignSubTaskToUser(listner: OnAdminAssignSubTaskToUserListener, subTask: ProjectSubTaskItem, userId: Int, position: Int)
    fun viewTeamMemberBySubTask(listener: OnAdminViewTeamMemeberBySubTask, subTask: ProjectSubTaskItem)
    fun viewSubTaskDetailByUser(listner: OnUserAdminViewSubTaskDetailListener, subTask: ProjectSubTaskItem)
    fun getSubTasksList(subTaskViewModel: ViewModelSubTask)
    fun viewSubTaskListByUser(subTaskViewModel: ViewModelSubTask, userId: String, taskId: String)

    fun storeNewProjectToServer(listener:OnCreateProjectListener,p: ProjectsItem)
    //fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel)
    fun getProjectList(listener: IDataManager.OnProjectListListener)

    fun createTask(viewModel: TaskViewModel, listener: OnAdminCreateTaskListener, taskItem: TaskItem)
    fun getAdminTaskList(viewModel: TaskViewModel, listener: OnAdminTaskListListener, projectId: Int)
    fun getUserTaskList(viewModel: TaskViewModel, id: String)
    fun updateTaskDetails(viewModel: TaskViewModel, listener: OnAdminTaskUpdatedListener, taskItem: TaskItem)
    fun getTeamMemberByTask(viewModel: TaskViewModel, listener: OnTaskMemberListener, taskItem: TaskItem)
    fun getMemberDetails(viewModel: TaskViewModel, addlistener: OnAddMemberDetailsListener, memberListListener: OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?)

    fun updateProject(listener: IDataManager.OnProjectListListener, p:ProjectsItem, index:Int)

    fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)

    /******************************************************************
     * Team Stuff
     ******************************************************************/
    fun createTeamForProject(listener:IDataManager.OnCreateTeamForProject,
                             projectId: Int,
                             team_member_userid: Int,
                             index:Int)

    fun getEmployeeList(listener:OnCreateTeamForProject)

}