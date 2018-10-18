package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.EmployeeListAdapter
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.databinding.FragTeamCreateForProjectBinding
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.frag_team_create_for_project.*
import kotlinx.android.synthetic.main.frag_team_create_for_project.view.*

class CreateTeamForProject:Fragment(), IDataManager.OnCreateTeamForProject {

    private val MIKKI_TEAM = "MikkiTeam"
    val viewModel = TeamViewModel()
    val adapter = EmployeeListAdapter()
    lateinit var bundleFrom:Bundle
    val bundleTo:Bundle = Bundle()
    var projectId:Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding:FragTeamCreateForProjectBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.frag_team_create_for_project,container,false)

        val view:View = binding.root

        binding.viewModel = viewModel
        bundleFrom = arguments
        var projectItem = bundleFrom.getParcelable<ProjectsItem>("data")
        bundleTo.putParcelable("data", projectItem)

        projectId = projectItem.id!!.toInt()

        showEmployeeList(view)
        Log.d(MIKKI_TEAM, "project id : " + projectId)

        setBtnClickHandler(projectId, view)


        return view
    }

    private fun setBtnClickHandler(projectId: Int, view:View) {
        adapter.setOnItemClickListener(object : EmployeeListAdapter.onItemClickListener{
            override fun onClick(view: View, employee: EmployeesItem, position: Int) {
                var employeeId = employee.empid!!.toInt()
                viewModel.addTeammateToProject(this@CreateTeamForProject,
                        projectId, employeeId, position)
            }

        })

        view.btn_cancel.setOnClickListener {
            val fragment = TeamForProjectFragment()
            fragment.arguments = bundleTo
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).commit()
            fragmentManager.popBackStack()
        }
        view.btn_done.setOnClickListener {

            var userId = view.et_team_userId.text.toString().toInt()
            Log.d(MIKKI_TEAM, "project_id: " + projectId + "user id: " + userId)

            if(userId != null){
                viewModel.addTeammateToProject(this@CreateTeamForProject,
                        projectId, userId, -1)

                val fragment = TeamForProjectFragment()
                fragment.arguments = bundleTo
                fragmentManager.beginTransaction().replace(R.id.mainActivity,
                        fragment).commit()
                fragmentManager.popBackStack()
            }else{
                Toast.makeText(context,
                        "you must enter a user id",
                        Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun showEmployeeList(view:View){
        Log.d(MIKKI_TEAM, "show employee list")

        view.rv_employee_list.layoutManager = LinearLayoutManager(context)
        view.rv_employee_list.adapter = adapter

        viewModel.initList(this)

    }

    override fun finishedInitialEmployeeList(item: EmployeesItem) {
        viewModel.updateList(item)
    }

    override fun finishedAddedMemberToProject(index: Int) {
        viewModel.removeAddedEmployeeFromView(index)

        Toast.makeText(context,
                "successfully added member to project",
                Toast.LENGTH_SHORT).show()

    }
}