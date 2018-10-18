package com.example.mikki.projectmanagement.view.subtask

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectSubTaskItem
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import kotlinx.android.synthetic.main.fragment_edit_sub_taskk.view.*

class EditSubTaskFragment: Fragment(), IDataManager.OnAdminEditSubTaskListener, IDataManager.OnTaskMemberListener {

    companion object {
        fun newInstance(): EditSubTaskFragment {
            return EditSubTaskFragment()
        }
        val dataManager: IDataManager = DataManager()
        var subTask = ProjectSubTaskItem()
        lateinit var viewmodel: ViewModelSubTask
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewmodel = ViewModelSubTask(context!!)//context?

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d("onCreateView", "started")
        val v = inflater!!.inflate(R.layout.fragment_edit_sub_taskk, container, false)

        if(arguments != null) {
            subTask = arguments.getParcelable("subtask")

            viewmodel.getTaskMemberListFromServer(this, subTask)

            v.etEditStProjectId.setText(subTask.projectid.toString())
            v.etEditStTaskId.setText(subTask.taskid.toString())
            v.etEditStName.setText(subTask.subtaskname.toString())
            v.etEditStStatus.setText(subTask.subtaskstatus.toString())
            v.etEditStDescription.setText(subTask.subtaskdesc.toString())
            v.etEditStStartDate.setText(subTask.startdate.toString())
            v.etEditStEndDate.setText(subTask.endstart.toString())

            v.tvEditStProjectId.setText(subTask.projectid)
            v.tvEditStTaskId.setText(subTask.taskid)
            v.tvEditStName.setText(subTask.subtaskname)
            v.tvEditStStatus.setText(subTask.subtaskstatus)
            v.tvEditStDescription.setText(subTask.subtaskdesc)
            v.tvEditStStartDate.setText(subTask.startdate + " - " + subTask.endstart)
        }

        v.btShowEditSubTask.setOnClickListener {
            v.viewSTLayout.visibility = View.GONE
            v.editSTLayout.visibility = View.VISIBLE
        }

        v.btBackSubTask.setOnClickListener {
            v.viewSTLayout.visibility = View.VISIBLE
            v.visibility = View.GONE
        }




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

    override fun getTaskMembers() {
        var manager = LinearLayoutManager(context.applicationContext)

        view.rv_subTaskMembers.layoutManager = manager
        view.rv_subTaskMembers.itemAnimator = DefaultItemAnimator()
        view.rv_subTaskMembers.adapter = viewmodel.memberRecyclerAdapter
    }
}