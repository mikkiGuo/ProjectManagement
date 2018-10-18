package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.MembersItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.subtaskmodel.ViewsubtasksItem
import com.example.mikki.projectmanagement.data.network.INetworkHelper

interface IDataManager:INetworkHelper {

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

}