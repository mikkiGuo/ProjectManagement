package com.example.mikki.projectmanagement.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.MemberDetails
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.view.task.TaskMemberRecyclerAdapter
import com.example.mikki.projectmanagement.view.task.TaskRecyclerAdapter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import kotlin.collections.ArrayList

class TaskViewModel(val context: Context): BaseObservable(), OnAddMemberDetailsListener {

    private val ninntag = AnkoLogger("ninntag")

    val iDataManager = DataManager()
    var taskRecyclerAdapter = TaskRecyclerAdapter(context, this)
    var memberRecyclerAdapter = TaskMemberRecyclerAdapter(context, this)

    @get:Bindable var taskList = ArrayList<TaskItem>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.taskList)
        }

    @get:Bindable var taskMemberList = ArrayList<TaskMemberItem>()
        set(value) {
            field = value
            notifyPropertyChanged(BR.taskMemberList)
        }

    fun createTask(listener: OnAdminCreateTaskListener, taskItem: TaskItem) {
        iDataManager.createTask(this, listener, taskItem)
    }

    fun isTaskCreated(listener: OnAdminCreateTaskListener, b: Boolean) {
        var s: String
        if (b) s = "Task Successfully Created" else s = "Project Not Found"
        listener.createTask(s)
    }

    fun getTaskListFromServer(listener: OnAdminTaskListListener, projectId: Int) {
        iDataManager.getAdminTaskList(this, listener, projectId)
    }

    fun showTaskList(listener: OnAdminTaskListListener, taskList: ArrayList<TaskItem>?) {
        this.taskList = taskList!!
        listener.getAdminTaskList()
    }

    fun updateTaskDetails(listener: OnAdminTaskUpdatedListener, taskItem: TaskItem) {
        iDataManager.updateTaskDetails(this, listener, taskItem)
    }

    fun isTaskUpdated(listener: OnAdminTaskUpdatedListener, b: Boolean) {
        var s: String
        if (b) s = "Task Details Updated" else s = "Project Not Found"
        listener.updateTask(s)
    }

    fun getTaskMemberListFromServer(listener: OnTaskMemberListener, taskItem: TaskItem) {
        ninntag.warn { "vm: getting task member list from server" }
        iDataManager.getTeamMemberByTask(this, listener, taskItem)
    }

    fun showTaskMemberList(listener: OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?) {
        ninntag.warn { "vm: trying to show member list" }
        if (memberList == null) {
            ninntag.warn { "vm: member list is null" }
            var memberItem = TaskMemberItem(memberdetails = MemberDetails(userfirstname = "None", userlastname = ""))
            this.taskMemberList.add(memberItem)
            listener.getTaskMembers()
            ninntag.warn { "vm: showtaskmemberlist: " + taskMemberList[0].memberdetails.toString() }
        } else {
            ninntag.warn { "vm: member list is not null going to get member details" }
            this.taskMemberList = memberList
            iDataManager.getMemberDetails(this, this, listener, taskMemberList)
            ninntag.warn { "vm: got member details"}
        }
    }

    fun addMemberDetailsToList(memberDetails: MemberDetails, position: Int) {
        taskMemberList.get(position).memberdetails = memberDetails
        ninntag.warn { "vm: added member details to list: " + taskMemberList.get(position).toString() }
    }

    fun assignMemberToTask(listener: IDataManager.OnAssignMemberListener, memberItem: TaskMemberItem) {
        iDataManager.assignMemberToTask(this, listener, memberItem)
    }

    fun isAssigned(listener: OnAssignMemberListener, b: Boolean) {
        var s: String
        if (b) s = "Member Successfully Assigned To This Task" else s = "Failed To Assign Member to Task"
        listener.assignMember(s)
    }

    override fun finishedAdding(listener: OnTaskMemberListener) {
        ninntag.warn { "taskviewmodel, showmemberlist: " + taskMemberList.toString() }
        listener.getTaskMembers()
    }

}