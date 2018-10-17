package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.MembersItem
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ViewsubtasksItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

    interface OnRegisterListener {
        fun isRegistered(boolean: Boolean)
    }

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


    interface OnAdminCreateTaskListener {
        fun createTask(string: String)
    }

    interface OnAdminTaskListListener {
        fun getAdminTaskList()
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
}