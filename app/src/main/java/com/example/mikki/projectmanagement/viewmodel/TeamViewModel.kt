package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.EmployeesItem


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


    fun initList(){
        Log.d(MIKKI_TEAM, "initList in view model")
        employeeList = mutableListOf()
        iDataManager.getEmployeeList(this)
    }

    fun updateList(employeesItem: EmployeesItem){
        employeeList.add(employeesItem)
        changedIndex = 0;
    }

    fun addTeammateToProject(projectId:Int, userId:Int, position: Int){
        Log.d(MIKKI_TEAM, "add teammate in view model")
        iDataManager.createTeamForProject(projectId, userId, position, this)

    }

    fun printMsg(msg:String){
        Log.d(MIKKI_TEAM, msg)
    }

    fun removeAddedEmployeeFromView(position:Int){
        Log.d(MIKKI_TEAM, "remove employee from list")
        employeeList.removeAt(position)
        if(position != -1){
            changedIndex = position
        }

    }


}