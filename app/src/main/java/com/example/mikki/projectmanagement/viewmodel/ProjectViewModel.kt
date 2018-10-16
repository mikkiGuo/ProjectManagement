package com.example.mikki.projectmanagement.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectList
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import kotlin.math.log


class ProjectViewModel:BaseObservable() {

    val dataManager:IDataManager = DataManager()

    @get:Bindable
    var projectList: MutableList<ProjectsItem> = mutableListOf()
        private set(value) {
            field = value
            notifyPropertyChanged(BR.projectList)
        }

    @get:Bindable
    var changedPositions: Int = 0
        private set(value) {
            field = value
            notifyPropertyChanged(BR.changedPositions)
        }

    fun initList() {
        dataManager.getProjectList(this)
    }

    fun updateList(projectsItem: ProjectsItem) {
        Log.d("mikkiproject", "update list called " + projectsItem.projectname)
        projectList.add(projectsItem)
        changedPositions = 0
    }

    fun addProject(projectsItem: ProjectsItem){
        Log.d("mikkiproject", "add project in view model "+projectsItem.projectname)
        dataManager.storeNewProjectToServer(projectsItem, this)
    }



}
