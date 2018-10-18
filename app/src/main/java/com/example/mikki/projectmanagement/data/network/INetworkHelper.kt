package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

interface INetworkHelper {

    fun register(listener: OnRegisterListener, register: Register)

    /******************************************************************
     * Project Stuff
     ******************************************************************/

    fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel)
    fun getProjectList(viewModel: ProjectViewModel)
    fun updateProject(p: ProjectsItem, viewModel: ProjectViewModel, index:Int)
    fun createTeamForProject(projectId: Int,
                             team_member_userid: Int,
                             index:Int,
                             viewModel: TeamViewModel)
    /******************************************************************
     * Task Stuff
     ******************************************************************/

    fun createTask(viewModel: TaskViewModel, listener: OnAdminCreateTaskListener, taskItem: TaskItem)
    fun getAdminTaskList(viewModel: TaskViewModel, listener: OnAdminTaskListListener, projectId: Int)
    fun getUserTaskList(viewModel: TaskViewModel, id: String)
    fun updateTaskDetails(viewModel: TaskViewModel, listener: OnAdminTaskUpdatedListener, taskItem: TaskItem)

    fun getTeamMemberByTask(viewModel: TaskViewModel, listener: OnTaskMemberListener, taskItem: TaskItem)
    fun getMemberDetails(viewModel: TaskViewModel, listener: OnAddMemberDetailsListener, memberListListener: OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?)
    fun assignMemberToTask(viewModel: TaskViewModel, listener: OnAssignMemberListener, memberItem: TaskMemberItem)
    fun getTeamMemberBySubTask(viewModelSubTask: ViewModelSubTask, listener: OnTaskMemberListener, subTaskItem: ProjectSubTaskItem)

    fun getMemberDetailsSubTask(viewModelSubTask: ViewModelSubTask, addlistener: OnAddMemberDetailsListener,
                         memberListListener: OnTaskMemberListener,
                         memberList: ArrayList<TaskMemberItem>?)

    /******************************************************************
     * SubTask Stuff
     ******************************************************************/

    fun storeNewSubTaskToServer(subTask: ProjectSubTaskItem)
    fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener, subTask: ProjectSubTaskItem)
    fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener, subTask: ProjectSubTaskItem)
    fun editSubTaskStatus(listner: OnUserEditSubTaskStatusListener, subTask: ProjectSubTaskItem)
    fun assignSubTaskToUser(listner: OnAdminAssignSubTaskToUserListener, subTask: ProjectSubTaskItem, userId: Int, position: Int)
    fun viewTeamMemberBySubTask(listener: OnAdminViewTeamMemeberBySubTask, subTask: ProjectSubTaskItem)
    fun viewSubTaskDetailByUser(listner: OnUserAdminViewSubTaskDetailListener, subTask: ProjectSubTaskItem)
    fun getSubTasksList(subTaskViewModel: ViewModelSubTask, taskId: String)
    fun viewSubTaskListByUser(subTaskViewModel: ViewModelSubTask, userId: String, taskId: String)

    /******************************************************************
     * Team Stuff
     ******************************************************************/

    fun getEmployeeList(viewModel: TeamViewModel)

}