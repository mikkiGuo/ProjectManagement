package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem

interface INetworkHelper {

    fun storeNewProjectToServer(p: ProjectsItem)

    fun getProjectList()

    fun storeNewSubTaskToServer(subTask:ProjectSubTaskItem)
}