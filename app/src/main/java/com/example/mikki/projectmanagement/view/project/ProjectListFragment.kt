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
import android.widget.Toast
import com.example.mikki.myrecyclerviewdatabinding.SwipeToDeleteCallback
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.ProjectListAdapter
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.projectmodel.ProjectsItem
import com.example.mikki.projectmanagement.databinding.FragProjectListBinding
import com.example.mikki.projectmanagement.viewmodel.ProjectViewModel
import kotlinx.android.synthetic.main.frag_project_list.view.*

class ProjectListFragment(): Fragment(), IDataManager.OnProjectListListener {



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
                viewModel.markCompleted( this@ProjectListFragment,
                        viewHolder!!.adapterPosition)
                adapter.removeAt(viewHolder!!.adapterPosition)

            }
        }


        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        view.rv_project_list.layoutManager = mLayoutManager
        view.rv_project_list.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(view.rv_project_list)

        viewModel.initList(this)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun finishedInitialList(item: ProjectsItem) {
        viewModel.updateList(item)
    }

    override fun finishedUpdateProject(p: ProjectsItem, index: Int) {
        Toast.makeText(context,
                "project, "+p.id+",marked completed",
                Toast.LENGTH_SHORT).show()
        viewModel.updateItem(index, p)
    }


}