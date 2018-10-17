package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.MembersItem
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

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

    interface OnUserAdminViewSubTaskDetailListener {
        fun viewSubTaskByDetailByUser(subTask: ProjectSubTaskItem)
    }

    interface OnUserEditSubTaskStatusListener {
        fun editSubTaskStatusByUser(message: String)
    }


    interface OnAdminCreateTaskListener {
        fun createTask()
    }

    interface OnAdminTaskListListener {
        fun getAdminTaskList(adminTaskList: ArrayList<ProjectAdminTaskItem>?)
    }

}