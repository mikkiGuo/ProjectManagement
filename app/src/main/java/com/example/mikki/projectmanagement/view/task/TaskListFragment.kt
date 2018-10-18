package com.example.mikki.projectmanagement.view.task

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.databinding.FragTaskListBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_details.view.*
import kotlinx.android.synthetic.main.frag_task_list.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.alert
import org.jetbrains.anko.warn

class TaskListFragment(): Fragment(), IDataManager.OnAdminTaskListListener {

    private val ninntag = AnkoLogger("ninntag")

    lateinit var viewmodel: TaskViewModel
    var projectID: Int? = null

    override fun onAttach(context: Context?) {
        var bundle = arguments
        projectID = bundle.getInt("projectId")

        viewmodel = TaskViewModel(context!!)
        viewmodel.getTaskListFromServer(this, projectID!!)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragTaskListBinding>(
                inflater, R.layout.frag_task_list, container, false)

        var v = binding.root
        binding.viewModel = viewmodel

        return v
    }

    override fun getTaskList() {
        if (viewmodel.taskList.size != 0) {
            val mLayoutManager = LinearLayoutManager(context)
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);

            view.rv_taskList.layoutManager = mLayoutManager
            view.rv_taskList.itemAnimator = DefaultItemAnimator()
            view.rv_taskList.adapter = viewmodel.taskRecyclerAdapter
        } else {
            alert {
                message = "No Task Exists"
                onCancelled {
                    activity.fragmentManager.popBackStack()
                }
            }.show()
        }
    }
}