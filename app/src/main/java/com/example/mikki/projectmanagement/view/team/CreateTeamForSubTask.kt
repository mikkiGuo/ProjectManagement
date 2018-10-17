package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.EmployeeListAdapter
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import com.example.mikki.projectmanagement.databinding.FragTeamCreateForProjectBinding
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.frag_team_create_for_project.view.*

class CreateTeamForSubTask:Fragment()  {
    private val MIKKI_TEAM = "MikkiTeam"
    val viewModel = TeamViewModel()
    val adapter = EmployeeListAdapter()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding:FragTeamCreateForProjectBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.frag_team_create_for_sub_task,container,false)

        val view:View = binding.root

        binding.viewModel = viewModel
        val bundle:Bundle = arguments
        var projectId = bundle.get("projectId")

        showEmployeeList(view)
        Log.d(MIKKI_TEAM, "project id : " + projectId as Int)

        setBtnClickHandler(projectId as Int, view)


        return view
    }

    private fun setBtnClickHandler(projectId: Int, view:View) {
        adapter.setOnItemClickListener(object : EmployeeListAdapter.onItemClickListener{
            override fun onClick(view: View, employee: EmployeesItem, position: Int) {
                var employeeId = employee.empid!!.toInt()
                viewModel.addTeammateToProject(projectId, employeeId, position)
            }

        })

        view.btn_cancel.setOnClickListener {
            val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }
        view.btn_done.setOnClickListener {

            var userId = view.et_team_userId.text.toString().toInt()
            Log.d(MIKKI_TEAM, "project_id: " + projectId + "user id: " + userId)
            viewModel.addTeammateToProject(projectId, userId, -1)
            /*val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()*/
        }

    }

    private fun showEmployeeList(view:View){
        Log.d(MIKKI_TEAM, "show employee list")

        view.rv_employee_list.layoutManager = LinearLayoutManager(context)
        view.rv_employee_list.adapter = adapter

        viewModel.initList()


    }
}