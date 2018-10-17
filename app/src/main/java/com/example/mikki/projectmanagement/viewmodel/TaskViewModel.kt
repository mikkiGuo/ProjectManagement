package com.example.mikki.projectmanagement.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.MemberDetails
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.data.model.TaskMemberItem
import com.example.mikki.projectmanagement.data.model.TaskMemberList
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
        iDataManager.getTeamMemberByTask(this, listener, taskItem)
    }

    fun showTaskMemberList(listener: OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?) {
        if (memberList == null) {
            var memberItem = TaskMemberItem(memberdetails = MemberDetails(userfirstname = "None", userlastname = ""))
            this.taskMemberList.add(memberItem)
            listener.getTaskMembers()
            ninntag.warn { "showtaskmemberlist: " + taskMemberList[0].memberdetails.toString() }
        } else {
            this.taskMemberList = memberList
            iDataManager.getMemberDetails(this, this, listener, taskMemberList)
            ninntag.warn { "in viewmodel"}
        }
    }

    fun addMemberDetailsToList(memberDetails: MemberDetails, position: Int) {
        taskMemberList.get(position).memberdetails = memberDetails
        ninntag.warn { "in addmemberdetails: " + taskMemberList.get(position).toString() }
    }

    override fun finishedAdding(listener: OnTaskMemberListener) {
        ninntag.warn { "taskviewmodel, showmemberlist: " + taskMemberList.toString() }
        listener.getTaskMembers()
    }

}