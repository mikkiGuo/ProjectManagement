package com.example.mikki.projectmanagement.view.subtask

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.adapter.SubTaskUserAdapter
import com.example.mikki.projectmanagement.databinding.FragmentSubTaskListByUserBinding

import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import kotlinx.android.synthetic.main.fragment_sub_task_list_by_user.view.*

class SubTaskFragmentListByUser : Fragment() {

    private val viewModelSubTask = ViewModelSubTask(context)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        val binding: FragmentSubTaskListByUserBinding
                = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sub_task_list, container, false)

        val view:View = binding.root

        val adapter = SubTaskUserAdapter()
        view.rvSubTaskByUser.layoutManager = LinearLayoutManager(context.applicationContext)
        view.rvSubTaskByUser.adapter = adapter

        binding.subTaskViewModel = viewModelSubTask
        viewModelSubTask.initList("1")

//        view.tvCreateSubTask.setOnClickListener {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.mainActivity, CreateSubTaskFragment.newInstance())
//                    .commit()
//        }

        return  view

    }

}