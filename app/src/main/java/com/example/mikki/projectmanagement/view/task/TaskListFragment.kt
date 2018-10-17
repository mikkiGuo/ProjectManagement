package com.example.mikki.projectmanagement.view.task

import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.databinding.FragTaskListBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_list.view.*

class TaskListFragment(): Fragment(), IDataManager.OnAdminTaskListListener {

    lateinit var viewmodel: TaskViewModel

    override fun onAttach(context: Context?) {
        viewmodel = TaskViewModel(context!!)
        viewmodel.createTaskList(this)

        super.onAttach(context)

        Log.d("ninntag", "onattachfragment: " + viewmodel.taskList.toString())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragTaskListBinding>(
                inflater, R.layout.frag_task_list, container, false)

        var v = binding.root
        binding.viewModel = viewmodel

        Log.d("ninntag", "oncreateviewfragment: " + viewmodel.taskList.toString())

        v.bt_updateTaskList.setOnClickListener {
            viewmodel.adapter.notifyDataSetChanged()
            Log.d("ninntag", "buttonclicked: " + viewmodel.taskList.toString())
        }

        return v
    }

    override fun getAdminTaskList(taskList: ArrayList<TaskItem>?) {
        var manager = LinearLayoutManager(context.applicationContext)

        view.rv_taskList.layoutManager = manager
        view.rv_taskList.itemAnimator = DefaultItemAnimator()
        view.rv_taskList.adapter = viewmodel.adapter

        Log.d("ninntag", "in getadmintasklist")
    }

}