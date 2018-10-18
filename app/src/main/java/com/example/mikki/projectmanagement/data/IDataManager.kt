package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.MembersItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.subtaskmodel.ViewsubtasksItem
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

    interface OnLoginListener{
        fun getUserInfo(result: LoginUserInfo)
        fun errorMsg(msg:String)
    }
    interface OnRegisterListener {
        fun isRegistered(boolean: Boolean)
    }

    /***************************************
     *          Task Stuff
     ***************************************/

    interface OnAdminCreateTaskListener {
        fun createTask(string: String)
    }

    interface OnAdminTaskListListener {
        fun getTaskList()
    }

    interface OnAdminTaskUpdatedListener {
        fun updateTask(s: String)
    }

    interface OnTaskMemberListener {
        fun getTaskMembers()
    }

    interface OnAddMemberDetailsListener {
        fun finishedAdding(listener: OnTaskMemberListener)
    }

    interface OnAssignMemberListener {
        fun assignMember(s: String)
    }

    /***************************************
     *          SubTask Stuff
     ***************************************/

    interface OnAdminCreateSubTaskListener {
        fun createTask(message: String)
    }

    interface OnAdminEditSubTaskListener {
        fun editTask(message: String)
    }

    interface OnAdminAssignSubTaskToUserListener {
        fun assignSubTask(message: String)
    }

    interface OnAdminViewTeamMemeberBySubTask {
        fun viewTeamMemberBySubTask(membersItem: ArrayList<MembersItem>?)
    }

    interface OnAdminViewSubTaskListByUser {
        fun viewSubTaskListByUser(viewsubtasksItem: ArrayList<ViewsubtasksItem>)
    }

    interface OnUserAdminViewSubTaskDetailListener {
        fun viewSubTaskByDetailByUser(subTask: ProjectSubTaskItem)
    }

    interface OnUserEditSubTaskStatusListener {
        fun editSubTaskStatusByUser(message: String)
    }

    interface OnCreateProjectListener{
        fun finishedOnCreateProject(p: ProjectsItem)
    }

    interface OnProjectListListener{
        fun finishedInitialList(p:ProjectsItem)
        fun finishedUpdateProject(p: ProjectsItem,
                                  index:Int)
    }

    interface OnCreateTeamForProject{
        fun finishedInitialEmployeeList(item:EmployeesItem)
        fun finishedAddedMemberToProject(index:Int)
    }

    interface OnDisplayProjectTeam{
        fun finishedGetProjectTeamList(item:ProjectteamItem)
        fun convertToEmployeeListFormat(item:TeamMemberDetail)
    }
}