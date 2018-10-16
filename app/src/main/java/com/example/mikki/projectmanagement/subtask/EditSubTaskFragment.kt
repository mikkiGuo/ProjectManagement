package com.example.mikki.projectmanagement.subtask

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.SubTaskAdapter
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import kotlinx.android.synthetic.main.fragment_edit_sub_task.view.*

class EditSubTaskFragment: Fragment(), SubTaskAdapter.OnClickHandler, IDataManager.OnAdminEditSubTaskListener {
    override fun sendData(subTaskItem: ProjectSubTaskItem) {
        //subTask = subTaskItem
    }


    companion object {
        fun newInstance(): EditSubTaskFragment {
            return EditSubTaskFragment()
        }
        val dataManager: IDataManager = DataManager()
        var subTask = ProjectSubTaskItem()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d("onCreateView", "started")
        val v = inflater!!.inflate(R.layout.fragment_edit_sub_task, container, false)

        if(arguments != null) {
            subTask = arguments.getParcelable("subtask")
        }



        v.etEditStProjectId.setText(subTask.projectid.toString())
        v.etEditStTaskId.setText(subTask.taskid.toString())
        v.etEditStName.setText(subTask.subtaskname.toString())
        v.etEditStStatus.setText(subTask.subtaskstatus.toString())
        v.etEditStDescription.setText(subTask.subtaskdesc.toString())
        v.etEditStStartDate.setText(subTask.startdate.toString())
        v.etEditStEndDate.setText(subTask.endstart.toString())


        v.btEditSubTask.setOnClickListener {

            subTask.projectid = v.etEditStProjectId.text.toString()
            subTask.taskid = v.etEditStTaskId.text.toString()
            subTask.subtaskname = v.etEditStName.text.toString()
            subTask.subtaskstatus = v.etEditStStatus.text.toString()
            subTask.subtaskdesc = v.etEditStDescription.text.toString()
            subTask.startdate = v.etEditStStartDate.text.toString()
            subTask.endstart = v.etEditStEndDate.text.toString()

            dataManager.editSubTask(this, subTask)
        }


        //taskid=1&project_id=27&userid=14&task_status=2&task_name=demo&task_desc=testing

        return v
    }

    override fun editTask(message: String) {
        Toast.makeText(view.context, message, Toast.LENGTH_LONG).show()
    }
}