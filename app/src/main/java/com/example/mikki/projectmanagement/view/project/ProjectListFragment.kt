package com.example.mikki.projectmanagement.view.project

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.myrecyclerviewdatabinding.SwipeToDeleteCallback
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.ProjectListAdapter
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.databinding.FragProjectListBinding
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
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

        binding.viewModel = viewModel

        val adapter = ProjectListAdapter()

        adapter.setOnItemClickListener(object :ProjectListAdapter.onItemClickListener{
            override fun onClick(view: View, project: ProjectsItem, position: Int) {
                Log.d("mikkiproject", "onclicked+++++++++++++++++++++++"+
                        project.projectname)

                var fragment = ProjectDetails()
                val bundle = Bundle()
                bundle.putParcelable("data", project)
                bundle.putInt("index", position)
                fragment.arguments = bundle
                fragmentManager.beginTransaction()
                        .replace(R.id.mainActivity, fragment)
                        .addToBackStack(null).commit()
            }
        })
        val swipeHandler = object : SwipeToDeleteCallback(context) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                //val adapter = view.rv_project_list.adapter as ProjectListAdapter
                viewModel.markCompleted(viewHolder!!.adapterPosition)
                adapter.removeAt(viewHolder!!.adapterPosition)

            }
        }

        view.rv_project_list.layoutManager = LinearLayoutManager(context)
        view.rv_project_list.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(view.rv_project_list)



        viewModel.initList()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}