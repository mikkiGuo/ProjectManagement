package com.example.mikki.projectmanagement

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem

class SubTaskFragment: Fragment() {

    companion object {
        fun newInstance(): SubTaskFragment {
            return SubTaskFragment()
        }

        val iDataManager: IDataManager = DataManager()
        var subTask = ProjectSubTaskItem()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        subTask.projectid = "76"
        subTask.taskid = "1"
        subTask.subtaskname = "Best SubTask"
        subTask.subtaskstatus = "1"
        subTask.subtaskdesc = "This is the best SubTask eva'"
        subTask.startdate = "2018-10-14"
        subTask.endstart = "2018-10-18"


        iDataManager.storeNewSubTaskToServer(subTask)

        iDataManager.getSubTaskList()

        return inflater?.inflate(R.layout.fragment_sub_task, container, false)
    }
}