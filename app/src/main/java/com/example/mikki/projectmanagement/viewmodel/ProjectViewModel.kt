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

    fun initList(listener:IDataManager.OnProjectListListener) {
        projectList = mutableListOf()
        dataManager.getProjectList(listener)
    }

    fun updateList(projectsItem: ProjectsItem) {
        Log.d("mikkiproject", "update list called " + projectsItem.projectname)
        projectList.add(projectsItem)
        changedPositions = 0
    }

    fun addProject(listener:IDataManager.OnCreateProjectListener ,projectsItem: ProjectsItem){
        Log.d("mikkiproject", "add project in view model "+projectsItem.projectname)
        dataManager.storeNewProjectToServer(listener, projectsItem)
    }

    fun updateProject(listener: IDataManager.OnProjectListListener, projectsItem: ProjectsItem, index:Int){
        dataManager.updateProject(listener, projectsItem, index)
    }

    fun updateItem(index:Int, p:ProjectsItem){
        var i = index+1
        projectList[index].copy(p.projectname,p.endstart,
                p.projectdesc,p.id,p.startdate,p.projectstatus)

    }

    fun markCompleted(listener: IDataManager.OnProjectListListener,
                      adapterPosition: Int) {
        projectList[adapterPosition].projectstatus = "2"
        updateProject(listener, projectList[adapterPosition],adapterPosition)
    }


}
