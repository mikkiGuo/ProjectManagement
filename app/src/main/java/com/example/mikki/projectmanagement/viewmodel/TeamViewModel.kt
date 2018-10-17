package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
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

    }

    @get:Bindable
    var changedIndex: Int = 0
    private set(value) {
        field = value
    }



    fun addTeammateToProject(projectId:Int, userId:Int){
        Log.d(MIKKI_TEAM, "add teammate in view model")
        //iDataManager.createTeamForProject(projectId, userId,this)
        iDataManager.getEmployeeList(this)
    }

    fun printMsg(msg:String){
        Log.d(MIKKI_TEAM, msg)
    }


}