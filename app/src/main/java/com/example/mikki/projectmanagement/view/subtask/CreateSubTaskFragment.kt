package com.example.mikki.projectmanagement.view.subtask

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import kotlinx.android.synthetic.main.fragment_create_sub_task.view.*

class CreateSubTaskFragment: Fragment(), IDataManager.OnAdminCreateSubTaskListener {

    companion object {
        fun newInstance(): CreateSubTaskFragment {
            return CreateSubTaskFragment()
        }
        val dataManager: IDataManager = DataManager()
        val subTask = ProjectSubTaskItem()

    }

    val me : Fragment = this

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.fragment_create_sub_task, container, false)

        v.btCreateSubTask.setOnClickListener {

            subTask.projectid = v.etStProjectId.text.toString()
            subTask.taskid = v.etStTaskId.text.toString()
            subTask.subtaskname = v.etStName.text.toString()
            subTask.subtaskstatus = v.etStStatus.text.toString()
            subTask.subtaskdesc = v.etStDescription.text.toString()
            subTask.startdate = v.etStStartDate.text.toString()
            subTask.endstart = v.etStEndDate.text.toString()

            dataManager.createNewSubTask(this, subTask)

        }

        return v
    }

    override fun createTask(message: String) {
        Toast.makeText(view.context, message, Toast.LENGTH_LONG).show()
        activity.fragmentManager.beginTransaction().remove(me).commit()
    }




}