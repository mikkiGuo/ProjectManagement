package com.example.mikki.projectmanagement.view.task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import kotlinx.android.synthetic.main.task_item_layout.view.*

class TaskRecyclerAdapter(val ADMIN_TASK_LIST: List<ProjectAdminTaskItem?>?):
        RecyclerView.Adapter<TaskRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.task_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        if (ADMIN_TASK_LIST != null)
            return ADMIN_TASK_LIST.size
        else return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tv_name.text = ADMIN_TASK_LIST!!.get(position)!!.taskname
        holder.tv_desc.text = ADMIN_TASK_LIST.get(position)!!.taskdesc
        holder.tv_start.text = ADMIN_TASK_LIST.get(position)!!.startdate
        holder.tv_end.text = ADMIN_TASK_LIST.get(position)!!.endstart
        holder.tv_status.text = ADMIN_TASK_LIST.get(position)!!.taskstatus
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tv_name = itemView.tv_taskName
        var tv_desc = itemView.tv_taskDesc
        var tv_start = itemView.tv_taskStart
        var tv_end = itemView.tv_taskEnd
        var tv_status = itemView.tv_taskStatus
    }

    companion object {

    }

}