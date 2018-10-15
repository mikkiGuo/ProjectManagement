package com.example.mikki.projectmanagement.project.projectlist

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.databinding.FragProjectListBinding
import com.example.mikki.projectmanagement.project.ProjectViewModel
import kotlinx.android.synthetic.main.frag_project_list.view.*

class ProjectListFragment(): Fragment() {

    private val viewModel = ProjectViewModel()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding:FragProjectListBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.frag_project_list,container,false)

        val view:View = binding.root

        val adapter = ProjectListAdapter()
        view.rv_project_list.layoutManager = LinearLayoutManager(context.applicationContext)
        view.rv_project_list.adapter = adapter

        binding.viewModel = viewModel

        viewModel.initList()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}