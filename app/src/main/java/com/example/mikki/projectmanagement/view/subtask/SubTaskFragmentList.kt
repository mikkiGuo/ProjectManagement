package com.example.mikki.projectmanagement.view.subtask

import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.BuildConfig
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.SubTaskAdapter
import com.example.mikki.projectmanagement.databinding.FragmentSubTaskListBinding

import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import kotlinx.android.synthetic.main.fragment_edit_sub_taskk.view.*
import kotlinx.android.synthetic.main.fragment_sub_task_list.view.*

class SubTaskFragmentList : Fragment() {

    lateinit var viewModelSubTask : ViewModelSubTask

    override fun onAttach(context: Context?) {
        viewModelSubTask = ViewModelSubTask(context!!)
        super.onAttach(context)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSubTaskListBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sub_task_list, container, false)

        val view:View = binding.root

        val adapter = SubTaskAdapter(context)
        var taskId = arguments.getInt("taskid").toString()

        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        view.rvSubTask.layoutManager = mLayoutManager
        view.rvSubTask.adapter = adapter

        binding.subTaskViewModel = viewModelSubTask
        viewModelSubTask.initList(taskId)

        if (BuildConfig.FLAVOR.equals("manager")) {
            Toast.makeText(context, "Manager LvL", Toast.LENGTH_SHORT).show()
            view.tvCreateSubTask.visibility = View.VISIBLE
        } else if (BuildConfig.FLAVOR.equals("developer")) {
            Toast.makeText(context, "Developer Lvl", Toast.LENGTH_SHORT).show()
            view.tvCreateSubTask.visibility = View.GONE
        }

        view.tvCreateSubTask.setOnClickListener {
            fragmentManager.beginTransaction()
                    .replace(R.id.mainActivity, CreateSubTaskFragment.newInstance())
                    .commit()
        }

        return  view

    }

}