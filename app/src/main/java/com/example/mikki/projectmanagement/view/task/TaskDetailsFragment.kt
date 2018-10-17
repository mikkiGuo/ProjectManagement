package com.example.mikki.projectmanagement.view.task

import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.databinding.FragTaskDetailsBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_details.view.*

class TaskDetailsFragment: Fragment(), IDataManager.OnAdminTaskUpdatedListener {

    lateinit var viewmodel: TaskViewModel
    lateinit var taskItem: TaskItem

    override fun onAttach(context: Context?) {
        viewmodel = TaskViewModel(context!!)
        var bundle = arguments
        taskItem = bundle.getParcelable("taskitem")

        Log.d("ninntag", taskItem.toString())

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

                v.et_details_projID.isEnabled = true
                v.et_details_projID.isClickable = true

                v.et_details_taskID.isEnabled = true
                v.et_details_taskID.isClickable = true

                v.et_details_userID.isEnabled = true
                v.et_details_userID.isClickable = true

                v.et_details_status.isEnabled = true
                v.et_details_status.isClickable = true

                v.et_details_name.isEnabled = true
                v.et_details_name.isClickable = true

                v.et_details_desc.isEnabled = true
                v.et_details_desc.isClickable = true

                v.et_details_start.isEnabled = true
                v.et_details_start.isClickable = true

                v.et_details_end.isEnabled = true
                v.et_details_end.isClickable = true

                v.bt_details_update.text = "Update Task Details"

            } else {

                var taskItem = TaskItem(
                        taskstatus = v.et_details_status.text.toString(),
                        taskdesc = v.et_details_desc.text.toString(),
                        endstart = v.et_details_end.text.toString(),
                        taskname = v.et_details_name.text.toString(),
                        startdate = v.et_details_start.text.toString(),
                        projectid = v.et_details_projID.text.toString(),
                        taskid = v.et_details_taskID.text.toString(),
                        userid = v.et_details_userID.text.toString()
                )

                Log.d("ninntag", taskItem.toString())

                v.et_details_projID.isEnabled = false
                v.et_details_projID.isClickable = false

                v.et_details_taskID.isEnabled = false
                v.et_details_taskID.isClickable = false

                v.et_details_userID.isEnabled = false
                v.et_details_userID.isClickable = false

                v.et_details_status.isEnabled = false
                v.et_details_status.isClickable = false

                v.et_details_name.isEnabled = false
                v.et_details_name.isClickable = false

                v.et_details_desc.isEnabled = false
                v.et_details_desc.isClickable = false

                v.et_details_start.isEnabled = false
                v.et_details_start.isClickable = false

                v.et_details_end.isEnabled = false
                v.et_details_end.isClickable = false

                viewmodel.updateTaskDetails(this, taskItem)

                v.bt_details_update.text = "Edit Task Details"

                onBackPressed(v)
            }
        }

        return v
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
}