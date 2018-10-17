package com.example.mikki.projectmanagement.view.task

import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.data.model.TaskMemberList
import com.example.mikki.projectmanagement.databinding.FragTaskDetailsBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_details.view.*

class TaskDetailsFragment: Fragment(), OnAdminTaskUpdatedListener, OnTaskMemberListener {

    lateinit var viewmodel: TaskViewModel
    lateinit var taskItem: TaskItem
    lateinit var taskMemberList: TaskMemberList

    override fun onAttach(context: Context?) {
        viewmodel = TaskViewModel(context!!)

        var bundle = arguments
        taskItem = bundle.getParcelable("taskitem")

        viewmodel.getTaskMemberListFromServer(this, taskItem)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragTaskDetailsBinding>(
                inflater, R.layout.frag_task_details, container, false)

        var v = binding.root

        binding.task = taskItem
        binding.executePendingBindings()

        v.bt_details_update.setOnClickListener {
            if (v.bt_details_update.text.equals("Edit Task Details")) {
                enableEditTexts(v, true)
                v.bt_details_update.text = "Update Task Details"
            } else {
                enableEditTexts(v, false)
                var taskItem = getUserInput(v)
                viewmodel.updateTaskDetails(this, taskItem)
                v.bt_details_update.text = "Edit Task Details"
                onBackPressed(v)
            }
        }

        return v
    }

    fun enableEditTexts(v: View, b: Boolean) {
        v.et_details_projID.isEnabled = b
        v.et_details_projID.isClickable = b

        v.et_details_taskID.isEnabled = b
        v.et_details_taskID.isClickable = b

        v.et_details_userID.isEnabled = b
        v.et_details_userID.isClickable = b

        v.et_details_status.isEnabled = b
        v.et_details_status.isClickable = b

        v.et_details_name.isEnabled = b
        v.et_details_name.isClickable = b

        v.et_details_desc.isEnabled = b
        v.et_details_desc.isClickable = b

        v.et_details_start.isEnabled = b
        v.et_details_start.isClickable = b

        v.et_details_end.isEnabled = b
        v.et_details_end.isClickable = b
    }

    fun getUserInput(v: View): TaskItem {
        return TaskItem(
                taskstatus = v.et_details_status.text.toString(),
                taskdesc = v.et_details_desc.text.toString(),
                endstart = v.et_details_end.text.toString(),
                taskname = v.et_details_name.text.toString(),
                startdate = v.et_details_start.text.toString(),
                projectid = v.et_details_projID.text.toString(),
                taskid = v.et_details_taskID.text.toString(),
                userid = v.et_details_userID.text.toString()
        )
    }

    fun onBackPressed(view: View) {
        view.setFocusableInTouchMode(true);
        view.requestFocus()
        view.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity.fragmentManager.popBackStack()
                activity.fragmentManager.popBackStack()

                var fragment = TaskListFragment()
                fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()

                true
            } else
            false
        }
    }

    override fun updateTask(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun getTaskMembers() {
        var manager = LinearLayoutManager(context.applicationContext)

        view.rv_taskMembers.layoutManager = manager
        view.rv_taskMembers.itemAnimator = DefaultItemAnimator()
        view.rv_taskMembers.adapter = viewmodel.memberRecyclerAdapter
    }
}