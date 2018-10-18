package com.example.mikki.projectmanagement.view.subtask

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem

class SubTaskDetailByUser: Fragment(), IDataManager.OnUserAdminViewSubTaskDetailListener {



    val dataManager: IDataManager = DataManager()
    var subTask = ProjectSubTaskItem()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater!!.inflate(R.layout.fragment_sub_task_detail_usert, container, false)

        dataManager.viewSubTaskDetailByUser(this, subTask)

        return v
    }

    override fun viewSubTaskByDetailByUser(subTask: ProjectSubTaskItem) {

    }
}