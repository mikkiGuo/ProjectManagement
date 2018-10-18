package com.example.mikki.projectmanagement.view.project

import android.app.Fragment
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.frag_project_create.view.*

class CreateProjectFragment : Fragment(), IDataManager.OnCreateProjectListener {

    private val viewModel = ProjectViewModel()
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.frag_project_create, container, false)

        view.tv_create_cnp.setOnClickListener {

            var newProject = newProjectInfo()

            Log.d("mikkiproject", "--------------------"+newProject.projectname)
            viewModel.addProject(this, newProject)

        }

        return view
    }

    private fun newProjectInfo(): ProjectsItem {
        var newProject = ProjectsItem()
        newProject.projectname = view.et_title_cnp.text.toString()
        newProject.projectstatus = "New"
        newProject.projectdesc = view.et_despt_cnp.text.toString()
        newProject.startdate = view.et_startdate_cnp.text.toString()
        newProject.endstart = view.et_enddate_cnp.text.toString()
        return newProject
    }

    override fun finishedOnCreateProject(p:ProjectsItem) {
        Toast.makeText(context,
                "project sucessfully created",
                Toast.LENGTH_SHORT).show()
        viewModel.updateList(p)
        val fragment = ProjectListFragment()
        fragmentManager.beginTransaction().replace(R.id.mainActivity,
                fragment).addToBackStack(null).commit()
    }

}