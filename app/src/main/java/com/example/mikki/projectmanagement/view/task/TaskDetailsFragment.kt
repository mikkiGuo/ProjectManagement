package com.example.mikki.projectmanagement.view.task

import android.app.Activity
import android.app.DatePickerDialog
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
import com.example.mikki.projectmanagement.BuildConfig
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager.*
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberList
import com.example.mikki.projectmanagement.databinding.FragTaskDetailsBinding
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_task_details.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import java.text.SimpleDateFormat
import java.util.*

class TaskDetailsFragment: Fragment(), OnAdminTaskUpdatedListener, OnTaskMemberListener {

    private val ninntag = AnkoLogger("ninntag")

    lateinit var viewmodel: TaskViewModel
    lateinit var taskItem: TaskItem
    lateinit var taskMemberList: TaskMemberList

    private val dateFormat = "yyyy-MM-dd"
    private val sdf = SimpleDateFormat(dateFormat, Locale.US)

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

        if (BuildConfig.FLAVOR.equals("developer")) {
            v.bt_details_update.visibility = View.GONE
            v.bt_details_assignUser.visibility = View.GONE
        }

        if (v.bt_details_update.text.equals("Edit Task Details")) {
            v.bt_edit_cancel.visibility = View.INVISIBLE
        }

        v.bt_details_update.setOnClickListener {
            if (v.bt_details_update.text.equals("Edit Task Details")) {
                enableEditTexts(v, true)
                v.bt_details_update.text = "Update Task Details"
                v.bt_edit_cancel.visibility = View.VISIBLE
            } else {
                enableEditTexts(v, false)
                var taskItem = getUserInput(v)
                viewmodel.updateTaskDetails(this, taskItem)
                v.bt_details_update.text = "Edit Task Details"
                v.bt_edit_cancel.visibility = View.INVISIBLE
                onBackPressed(v)
            }
        }

        v.bt_edit_cancel.setOnClickListener {
            if (v.bt_details_update.text.equals("Edit Task Details")) {
                v.bt_edit_cancel.visibility = View.INVISIBLE
            } else {
                v.bt_edit_cancel.visibility = View.INVISIBLE
                enableEditTexts(v, false)
                v.bt_details_update.text = "Edit Task Details"
            }
        }

        v.bt_details_showSubTasks.setOnClickListener {
            var fragment = SubTaskFragmentList()
            var bundle = Bundle()
            bundle.putInt("taskid", taskItem.taskid!!.toInt())
            fragment.arguments = bundle
            (context as Activity).fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        v.et_details_start.setOnClickListener {
            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                v.et_details_start.setText(sdf.format(selectedDate))
            }, 2000, 0, 1)
            dpd.show()
        }

        v.et_details_end.setOnClickListener {
            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                v.et_details_start.setText(sdf.format(selectedDate))
            }, 2000, 0, 1)
            dpd.show()
        }

        v.bt_details_assignUser.setOnClickListener {
            val dialogFragment = AssignToTaskDialogFragment()

            var taskMemberItem = TaskMemberItem(projectid = taskItem.projectid, taskid = taskItem.taskid)

            var bundle = Bundle()
            bundle.putParcelable("taskmember", taskMemberItem)
            bundle.putParcelable("taskitem", taskItem)
            dialogFragment.arguments = bundle

            dialogFragment.show(activity.fragmentManager, null)
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