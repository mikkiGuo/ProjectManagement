package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import kotlinx.android.synthetic.main.frag_team_for_project.view.*

class TeamForProjectFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view:View = inflater!!.inflate(R.layout.frag_team_for_project,
                container, false)

        view.icon_add_member_project.setOnClickListener{
            val bundle:Bundle = arguments
            var projectId = bundle.get("projectId")

            val fragment = CreateTeamForProject()
            val bundle2 = Bundle()
            bundle2.putInt("projectId", projectId as Int)
            fragment.arguments = bundle2
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }
        return view
    }
}