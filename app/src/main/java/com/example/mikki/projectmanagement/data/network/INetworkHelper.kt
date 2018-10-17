package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel

interface INetworkHelper {

    //fun storeNewProjectToServer(p: ProjectsItem)

    fun createNewSubTask(listener: IDataManager.OnAdminCreateSubTaskListener,
                         subTask:ProjectSubTaskItem)

    fun editSubTask(listener: IDataManager.OnAdminEditSubTaskListener,
                         subTask:ProjectSubTaskItem)

    fun editSubTaskStatus(listner: OnUserEditSubTaskStatusListener,
                          subTask: ProjectSubTaskItem)



    fun assignSubTaskToUser(listner: OnAdminAssignSubTaskToUserListener,
                            subTask: ProjectSubTaskItem, userId: Int, position: Int)

    fun viewTeamMemberBySubTask(listener: OnAdminViewTeamMemeberBySubTask, subTask: ProjectSubTaskItem)

    fun viewSubTaskDetailByUser(listner: OnUserAdminViewSubTaskDetailListener,
                                subTask: ProjectSubTaskItem)



    fun getSubTasksList(subTaskViewModel: ViewModelSubTask)
    fun viewSubTaskListByUser(subTaskViewModel: ViewModelSubTask, userId: String, taskId: String)
    fun storeNewProjectToServer(p: ProjectsItem, viewModel: ProjectViewModel)
    fun getProjectList(viewModel: ProjectViewModel)
    fun updateProject(p:ProjectsItem, viewModel: ProjectViewModel, index:Int)

    fun createTask(listener: OnAdminCreateTaskListener, adminTaskItem: ProjectAdminTaskItem)
    fun getAdminTaskList(listener: OnAdminTaskListListener)
    fun getUserTaskList(id: String)
    //fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)
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