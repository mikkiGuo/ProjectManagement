package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem


class TeamViewModel: BaseObservable() {

    private val MIKKI_TEAM = "MikkiTeam"

    val iDataManager:IDataManager = DataManager()

    @get:Bindable
    var employeeList:MutableList<EmployeesItem> = mutableListOf()
    private set(value) {
        field = value
        notifyPropertyChanged(BR.employeeList)
    }

    @get:Bindable
    var changedIndex: Int = 0
    private set(value) {
        field = value
        notifyPropertyChanged(BR.changedIndex)
    }

    @get:Bindable
    var projectTeamList:MutableList<EmployeesItem> = mutableListOf()
    private set(value) {
        field = value
        notifyPropertyChanged(BR.projectList)
    }

    @get:Bindable
    var employeeListFrag:MutableList<EmployeesItem> = mutableListOf()
    private set(value) {
        field = value
        notifyPropertyChanged(BR.employeeList)
    }

    fun initList(listener:IDataManager.OnCreateTeamForProject){
        Log.d(MIKKI_TEAM, "initList in view model")
        employeeList = mutableListOf()
        iDataManager.getEmployeeList(listener)
    }

    fun updateList(employeesItem: EmployeesItem){
        employeeList.add(employeesItem)
        changedIndex = 0;
    }

    fun addTeammateToProject(listener:IDataManager.OnCreateTeamForProject,
                             projectId:Int, userId:Int, position: Int){
        Log.d(MIKKI_TEAM, "add teammate in view model")
        iDataManager.createTeamForProject(listener, projectId, userId, position)

    }

    fun addTeammateToSubTask(listener: IDataManager.OnAdminAssignSubTaskToUserListener,
                             subTaskItem: ProjectSubTaskItem, userId:Int, position: Int){
        Log.d(MIKKI_TEAM, "add teammate in view model")
        iDataManager.assignSubTaskToUser(listener, subTaskItem, userId, position)

    }

    fun printMsg(msg:String){
        Log.d(MIKKI_TEAM, msg)
    }

    fun removeAddedEmployeeFromView(position:Int){
        Log.d(MIKKI_TEAM, "remove employee from list")
        employeeList.removeAt(position)

        var newList = employeeList.toMutableList()
        employeeList = mutableListOf()

        for(item in newList){
            updateList(item)
        }

    }

    /***
     * project team list methods
     */
    fun initListProjectTeam(listener: IDataManager.OnDisplayProjectTeam,
                            projectId: Int){
        Log.d(MIKKI_TEAM, "initListProjectTeam in view model")
        projectTeamList = mutableListOf()
        iDataManager.getProjectTeamList(listener,projectId)
    }

    fun getMemberDetailById(listener: IDataManager.OnDisplayProjectTeam,
                            memberId: String){
        Log.d(MIKKI_TEAM, "getMemberDetailById: $memberId")
        iDataManager.getMemberDetailForProjectTeam(listener,memberId)

    }

    fun updateMemberList(item: EmployeesItem){
        projectTeamList.add(item)
        changedIndex = 0;
    }

    fun updateListEmployeeFrag(employeesItem: EmployeesItem){
        employeeListFrag.add(employeesItem)
        changedIndex = 0;
    }


}