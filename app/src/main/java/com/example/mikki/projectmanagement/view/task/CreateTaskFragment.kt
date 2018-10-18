package com.example.mikki.projectmanagement.view.task

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_create.view.*

class CreateTaskFragment: Fragment(), IDataManager.OnAdminCreateTaskListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.frag_task_create, container, false)

        val viewModel = TaskViewModel(v.context)

        v.bt_createTask.setOnClickListener{
            val task = TaskItem()

            task.projectid = v.et_create_projectID.text.toString()
            task.taskname = v.et_create_taskName.text.toString()
            task.taskdesc = v.et_create_taskDesc.text.toString()
            task.taskstatus = v.et_create_taskStatus.text.toString()
            task.startdate = v.et_create_taskStart.text.toString()
            task.endstart = v.et_create_taskEnd.text.toString()

            viewModel.createTask(this, task)
        }

        return v
    }

    override fun createTask(string: String) {
        Toast.makeText(view.context, string, Toast.LENGTH_SHORT).show()
    }

}