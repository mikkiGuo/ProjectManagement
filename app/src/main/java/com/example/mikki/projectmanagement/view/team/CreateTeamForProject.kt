package com.example.mikki.projectmanagement.view.team

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import kotlinx.android.synthetic.main.frag_team_create_for_project.view.*

class CreateTeamForProject:Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view:View = inflater!!.inflate(R.layout.frag_team_create_for_project,
                container, false)

        view.btn_cancel.setOnClickListener {
            val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }
        view.btn_done.setOnClickListener {


            val fragment = TeamForProjectFragment()
            fragmentManager.beginTransaction().replace(R.id.mainActivity,
                    fragment).addToBackStack(null).commit()
        }
        return view
    }
}