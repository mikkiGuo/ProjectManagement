package com.example.mikki.projectmanagement.data

import com.example.mikki.projectmanagement.data.model.Project
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper

class DataManager:IDataManager {

    companion object {
        val iNetworkHelper: INetworkHelper = NetworkHelper()
    }

    override fun storeNewProjectToServer(p: Project) {
        iNetworkHelper.storeNewProjectToServer(p)
    }

}