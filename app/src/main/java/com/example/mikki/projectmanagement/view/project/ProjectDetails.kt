package com.example.mikki.projectmanagement.view.project

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mikki.projectmanagement.BuildConfig
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import com.example.mikki.projectmanagement.view.team.TeamForProjectFragment
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.frag_project_details.view.*

class ProjectDetails:Fragment(), IDataManager.OnProjectListListener {

    private val viewModel = ProjectViewModel()
    lateinit var bundle:Bundle
    lateinit var projectItem:ProjectsItem


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.frag_project_details,
                container, false)

        setValueToUI(view)
        setEnableFalse(view)

        view.btn_edit_project.setOnClickListener{
            setEnableTrue(view)
        }

        view.btn_update_project.setOnClickListener{

            var index = bundle.get("index")
            var updatedProject = setUpdatedProject(view)

            viewModel.updateProject(this, updatedProject, index as Int)

        }

        view.tv_add_teammates.setOnClickListener {
            var fragment = TeamForProjectFragment()

            val bundle = Bundle()
            bundle.putInt("projectId", projectItem.id!!.toInt())
            fragment.arguments = bundle

            fragmentManager.beginTransaction()
                    .replace(R.id.mainActivity, fragment)
                    .addToBackStack(null).commit()
        }

        view.btn_view_tasks.setOnClickListener {
            var fragment = TaskListFragment()

            val bundle = Bundle()
            bundle.putInt("projectId", projectItem.id!!.toInt())
            fragment.arguments = bundle

            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        return view
    }



    private fun setUpdatedProject(view: View):ProjectsItem {
        var updatedProject = ProjectsItem()
        updatedProject.id = projectItem.id
        updatedProject.projectname = view.tv_title_cnp.text.toString()
        updatedProject.projectdesc = view.tv_despt_cnp.text.toString()
        updatedProject.startdate = view.tv_startdate_cnp.text.toString()
        updatedProject.endstart = view.tv_enddate_cnp.text.toString()

        var status = view.spinner_project_status.selectedItemPosition

        updatedProject.projectstatus = status.toString()

        return updatedProject
    }

    private fun setEnableFalse(view:View){
        view.tv_title_cnp.isEnabled = false
        view.tv_despt_cnp.isEnabled = false
        view.tv_enddate_cnp.isEnabled = false
        view.tv_startdate_cnp.isEnabled = false
        view.btn_update_project.visibility = View.INVISIBLE
        view.spinner_project_status.isEnabled = false
    }

    private fun setEnableTrue(view:View){
        view.tv_title_cnp.isEnabled = true
        view.tv_despt_cnp.isEnabled = true
        view.tv_enddate_cnp.isEnabled = true
        view.tv_startdate_cnp.isEnabled = true
        view.btn_edit_project.visibility = View.GONE
        view.btn_update_project.visibility = View.VISIBLE
        view.spinner_project_status.isEnabled = true
    }

    private fun setValueToUI(view:View){
        bundle = arguments
        projectItem = bundle.getParcelable<ProjectsItem>("data")

        view.tv_title_cnp.setText(projectItem.projectname)
        view.tv_despt_cnp.setText(projectItem.projectdesc)
        view.tv_enddate_cnp.setText(projectItem.endstart)
        view.tv_startdate_cnp.setText(projectItem.startdate)

        var statusPos = projectItem.projectstatus!!.toInt()
        view.spinner_project_status.setSelection(statusPos)
    }

    override fun finishedInitialList(p: ProjectsItem) {
        //do nothing in this fragment
    }

    override fun finishedUpdateProject(p: ProjectsItem, index: Int) {
        Toast.makeText(context,
                "Successfully updated project",
                Toast.LENGTH_SHORT).show()
        var fragment = ProjectListFragment()
        fragmentManager.beginTransaction()
                .replace(R.id.mainActivity, fragment)
                .addToBackStack(null).commit()

    }
}