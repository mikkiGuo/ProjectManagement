package com.example.mikki.projectmanagement.view.task

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.TaskItem
import com.example.mikki.projectmanagement.databinding.ItemTaskLayoutBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel

class TaskRecyclerAdapter(val context: Context, var viewModel: TaskViewModel):
        RecyclerView.Adapter<TaskRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskLayoutBinding>(
                LayoutInflater.from(parent.context), R.layout.item_task_layout, parent, false)

        return MyViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return viewModel.taskList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var taskItem = viewModel.taskList.get(position)
        holder.bind(taskItem)

        holder.itemView.setOnClickListener {
            val fragment = TaskDetailsFragment()

            var bundle = Bundle()
            bundle.putParcelable("taskitem", taskItem)
            fragment.arguments = bundle

            (context as Activity).fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }
    }

    class MyViewHolder(itemView: View, val binding: ItemTaskLayoutBinding): RecyclerView.ViewHolder(itemView) {
        fun bind(taskItem: TaskItem) {
            binding.setVariable(BR.task, taskItem)
        }
    }
}