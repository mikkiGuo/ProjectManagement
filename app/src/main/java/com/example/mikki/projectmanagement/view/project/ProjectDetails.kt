package com.example.mikki.projectmanagement.view.project

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.frag_project_details.view.*

class ProjectDetails:Fragment() {
    private val viewModel = ProjectViewModel()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater!!.inflate(R.layout.frag_project_details,
                container, false)

        /*var statusData = resources.getStringArray(R.array.spinner_status)
        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(view.context, android.R.layout.simple_spinner_item,
                statusData)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        view.spinner_project_status!!.setAdapter(aa)*/

        var bundle = arguments
        var projectItem = bundle.getParcelable<ProjectsItem>("data")
        var index = bundle.get("index")
        Log.d("mikkitest", "" + projectItem.projectname)


        view.tv_title_cnp.setText(projectItem.projectname)
        view.tv_despt_cnp.setText(projectItem.projectdesc)
        view.tv_enddate_cnp.setText(projectItem.endstart)
        view.tv_startdate_cnp.setText(projectItem.startdate)

        setEnableFalse(view)

        view.btn_edit_project.setOnClickListener{
            setEnableTrue(view)
        }

        view.btn_update_project.setOnClickListener{
            var updatedProject = ProjectsItem()

            updatedProject.id = projectItem.id
            updatedProject.projectname = view.tv_title_cnp.text.toString()
            updatedProject.projectdesc = view.tv_despt_cnp.text.toString()
            updatedProject.startdate = view.tv_startdate_cnp.text.toString()
            updatedProject.endstart = view.tv_enddate_cnp.text.toString()

            updatedProject.projectstatus = "1"
            Log.d("mikki", updatedProject.projectstatus)

            Log.d("mikkiindex", ""+index)
            viewModel.updateProject(updatedProject, index as Int)

            var fragment = ProjectListFragment()
            fragmentManager.beginTransaction()
                    .replace(R.id.mainActivity, fragment)
                    .addToBackStack(null).commit()

        }

        return view
    }

    private fun setEnableFalse(view:View){
        view.tv_title_cnp.isEnabled = false
        view.tv_despt_cnp.isEnabled = false
        view.tv_enddate_cnp.isEnabled = false
        view.tv_startdate_cnp.isEnabled = false
        view.btn_update_project.visibility = View.INVISIBLE
    }

    private fun setEnableTrue(view:View){
        view.tv_title_cnp.isEnabled = true
        view.tv_despt_cnp.isEnabled = true
        view.tv_enddate_cnp.isEnabled = true
        view.tv_startdate_cnp.isEnabled = true
        view.btn_edit_project.visibility = View.GONE
        view.btn_update_project.visibility = View.VISIBLE

    }
}