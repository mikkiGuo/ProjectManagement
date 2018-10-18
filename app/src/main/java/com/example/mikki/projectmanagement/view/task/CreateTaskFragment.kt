package com.example.mikki.projectmanagement.view.task

import android.app.DatePickerDialog
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
import kotlinx.android.synthetic.main.frag_task_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class CreateTaskFragment: Fragment(), IDataManager.OnAdminCreateTaskListener {

    private val dateFormat = "yyyy-MM-dd"
    private val sdf = SimpleDateFormat(dateFormat, Locale.US)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.frag_task_create, container, false)

        val viewModel = TaskViewModel(v.context)

        v.bt_createTask.setOnClickListener{
            val task = TaskItem()

            task.projectid = v.et_create_projID.text.toString()
            task.taskname = v.et_create_name.text.toString()
            task.taskdesc = v.et_create_desc.text.toString()
            task.taskstatus = v.et_create_status.text.toString()
            task.startdate = v.et_create_start.text.toString()
            task.endstart = v.et_create_end.text.toString()

            viewModel.createTask(this, task)
        }

        v.bt_create_cancel.setOnClickListener {
            activity.fragmentManager.popBackStack()
        }

        v.et_create_start.setOnClickListener {
            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                v.et_details_start.setText(sdf.format(selectedDate))
            }, 2000, 0, 1)
            dpd.show()
        }

        v.et_create_end.setOnClickListener {
            val dpd = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val selectedDate = GregorianCalendar(year, monthOfYear, dayOfMonth).time
                v.et_details_start.setText(sdf.format(selectedDate))
            }, 2000, 0, 1)
            dpd.show()
        }

        return v
    }

    override fun createTask(string: String) {
        Toast.makeText(view.context, string, Toast.LENGTH_SHORT).show()
    }

}