package com.example.mikki.projectmanagement.view.task

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import kotlinx.android.synthetic.main.frag_task_list.view.*

class TaskListFragment(): Fragment() {

    var adminTaskList: ArrayList<ProjectAdminTaskItem>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        var bundle = arguments
        adminTaskList = bundle.getParcelableArrayList("tasklist")

        Log.d("ninntag", "fragment attached")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.frag_task_list, container, false)

        var manager = LinearLayoutManager(context.applicationContext)
        v.rv_taskList.layoutManager = manager
        v.rv_taskList.itemAnimator = DefaultItemAnimator()

        var adapter = TaskRecyclerAdapter(ADMIN_TASK_LIST = adminTaskList)
        v.rv_taskList.adapter = adapter

        Log.d("ninntag", "fragment oncreateview")

        return v
    }
}