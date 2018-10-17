package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.frag_team_create_for_project.view.*

class CreateTeamForProject:Fragment() {
    private val MIKKI_TEAM = "MikkiTeam"

    val viewModel = TeamViewModel()
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view:View = inflater!!.inflate(R.layout.frag_team_create_for_project,
                container, false)

        view.btn_cancel.setOnClickListener {
            val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }
        view.btn_done.setOnClickListener {
            val bundle:Bundle = arguments
            var projectId = bundle.get("projectId")
            var userId = view.et_team_userId.text.toString().toInt()
            Log.d(MIKKI_TEAM, "project_id: " + projectId + "user id: " + userId)

            viewModel.addTeammateToProject(projectId as Int, userId)
            /*val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()*/
        }
        return view
    }
}