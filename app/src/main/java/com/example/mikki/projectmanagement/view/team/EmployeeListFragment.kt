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
import com.example.mikki.projectmanagement.adapter.DisplayTeamListAdapter
import com.example.mikki.projectmanagement.adapter.EmployeeListAdapter
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import com.example.mikki.projectmanagement.databinding.FragEmployeeListBinding
import com.example.mikki.projectmanagement.databinding.FragTeamCreateForProjectBinding
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.frag_employee_list.view.*
import kotlinx.android.synthetic.main.frag_team_create_for_project.view.*

class EmployeeListFragment:Fragment(), IDataManager.OnCreateTeamForProject {


    private val MIKKI_TEAM = "MikkiTeam"
    val viewModel = TeamViewModel()
    val adapter = DisplayTeamListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding: FragEmployeeListBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.frag_employee_list,container,false)

        val view:View = binding.root
        binding.viewModel = viewModel

        showEmployeeList(view)

        return view
    }

    private fun showEmployeeList(view:View){
        Log.d(MIKKI_TEAM, "show employee list")
        view.rv_employee_list_without_add.layoutManager = LinearLayoutManager(context)
        view.rv_employee_list_without_add.adapter = adapter

        viewModel.initList(this)

        adapter.setOnItemClickListener(object :DisplayTeamListAdapter.OnItemClickListener{
            override fun onClick(view: View, data: EmployeesItem) {
                //TODO: perform actions
                Toast.makeText(context, "employee clicked", Toast.LENGTH_LONG).show()
            }
        })


    }

    override fun finishedInitialEmployeeList(item: EmployeesItem) {
        viewModel.updateListEmployeeFrag(item)
    }

    override fun finishedAddedMemberToProject(index: Int) {
        //do nothing
    }
}