package com.example.mikki.projectmanagement.view.project

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mikki.projectmanagement.R
import kotlinx.android.synthetic.main.frag_project_details.view.*

class ProjectDetails:Fragment() {

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



        return view
    }
}