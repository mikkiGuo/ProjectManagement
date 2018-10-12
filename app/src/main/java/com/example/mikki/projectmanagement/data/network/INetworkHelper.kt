package com.example.mikki.projectmanagement.data.network

import com.example.mikki.projectmanagement.data.model.Project

interface INetworkHelper {

    fun storeNewProjectToServer(p: Project)
}