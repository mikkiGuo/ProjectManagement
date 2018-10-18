package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.DisplayTeamListAdapter
import com.example.mikki.projectmanagement.adapter.EmployeeListAdapter
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.*
import com.example.mikki.projectmanagement.databinding.FragTeamForProjectBinding
import com.example.mikki.projectmanagement.view.project.ProjectDetails
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.frag_team_for_project.view.*
import kotlinx.android.synthetic.main.item_name_tag.view.*

class TeamForProjectFragment:Fragment(), IDataManager.OnDisplayProjectTeam {

    private val MIKKI_TEAM = "MikkiTeam"
    val viewModel = TeamViewModel()
    val adapter = DisplayTeamListAdapter()
    lateinit var bundleFrom:Bundle
    val bundleTo:Bundle = Bundle()
    var projectId:Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val binding:FragTeamForProjectBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.frag_team_for_project,container,false)

        val view:View = binding.root

        binding.viewModel = viewModel

        bundleFrom = arguments
        var projectItem = bundleFrom.getParcelable<ProjectsItem>("data")
        bundleTo.putParcelable("data", projectItem)

        projectId = projectItem.id!!.toInt()
        Log.d(MIKKI_TEAM, "projectId" + projectId)

        view.rv_project_team.layoutManager = LinearLayoutManager(context)
        view.rv_project_team.adapter = adapter

        viewModel.initListProjectTeam(this, projectId)

        btnClickHandler(view)
        return view
    }

    private fun btnClickHandler(view:View) {

        view.icon_add_member_project.setOnClickListener{
            val fragment = CreateTeamForProject()

            fragment.arguments = bundleTo
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }

        view.btn_team_ok.setOnClickListener {
            val fragment = ProjectDetails()
            fragment.arguments = bundleTo
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }

        adapter.setOnItemClickListener(object :DisplayTeamListAdapter.OnItemClickListener{
            override fun onClick(view: View, data: EmployeesItem) {
                //do nothing
            }
        })

    }

    override fun finishedGetProjectTeamList(item: ProjectteamItem) {
        viewModel.getMemberDetailById(this, item.teammemberuserid!!)
    }

    override fun convertToEmployeeListFormat(item: TeamMemberDetail) {
        var employee = EmployeesItem(
                empid = item.userid,
                empfirstname = item.userfirstname,
                emplastname = item.userlastname,
                empmobile = item.usermobile,
                empemail = item.useremail
        )
        Log.d(MIKKI_TEAM, employee.toString())
        viewModel.updateMemberList(employee)
    }

}