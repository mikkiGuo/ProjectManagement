package com.example.mikki.projectmanagement.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.adapter.SubTaskMemberRecyclerAdapter
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem

class ViewModelSubTask(context: Context) : BaseObservable(),
        IDataManager.OnUserAdminViewSubTaskDetailListener, IDataManager.OnAddMemberDetailsListener {


    override fun viewSubTaskByDetailByUser(subTask: ProjectSubTaskItem) {

    }

    val dataManager:IDataManager = DataManager()
    var memberRecyclerAdapter = SubTaskMemberRecyclerAdapter(context, this)

    @get:Bindable
    var subTaskList: MutableList<ProjectSubTaskItem> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.subTaskList)
        }

    @get:Bindable
    var subTaskListByUser = ArrayList<TaskMemberItem>()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.subTaskListByUser)
        }

    @get:Bindable
    var changedPositions: Int = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.changedPositions)
        }

    fun initList() {
        dataManager.getSubTasksList(this)
        //dataManager.viewSubTaskListByUser(this, "3", "3")
    }

    fun upadteSubTaskList(subTaskItem: ProjectSubTaskItem) {
        subTaskList.add(subTaskItem)
        changedPositions = 0
    }

    fun getTaskMemberListFromServer(listener: IDataManager.OnTaskMemberListener, subTaskItem: ProjectSubTaskItem) {
        Log.d("vm getMemberFrServer", subTaskItem.toString())
        dataManager.getTeamMemberBySubTask(this, listener, subTaskItem)
    }

    fun showTaskMemberList(listener: IDataManager.OnTaskMemberListener, memberList: ArrayList<TaskMemberItem>?) {
        Log.d("vm tryingShowMembList", memberList.toString())
        if (memberList == null) {
            Log.d("vm membList", "is null")
            var memberItem = TaskMemberItem(memberdetails = MemberDetails(userfirstname = "None", userlastname = ""))
            this.subTaskListByUser.add(memberItem)
            listener.getTaskMembers()
            //ninntag.warn { "showtaskmemberlist: " + taskMemberList[0].memberdetails.toString() }
        } else {
            this.subTaskListByUser = memberList
            Log.d("vm membList", subTaskListByUser.toString())
            dataManager.getMemberDetailsSubTask(this, this, listener, subTaskListByUser)
            //ninntag.warn { "in viewmodel"}
        }
    }

    fun addMemberDetailsToList(memberDetails: MemberDetails, position: Int) {
        subTaskListByUser.get(position).memberdetails = memberDetails
        Log.d("addMemberDetailsToList", subTaskListByUser.get(position).toString())
        //ninntag.warn { "in addmemberdetails: " + subTaskListByUser.get(position).toString() }
    }

    override fun finishedAdding(listener: IDataManager.OnTaskMemberListener) {
        Log.d("vm finishedAdding", "")
        listener.getTaskMembers()
    }

//    fun upadteSubTaskListByUser(viewsubtasksItem: ViewsubtasksItem) {
//        subTaskListByUser.add(viewsubtasksItem)
//        changedPositions = 0
//    }
}