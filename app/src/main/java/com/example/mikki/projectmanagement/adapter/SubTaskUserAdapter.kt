package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ViewsubtasksItem
import kotlinx.android.synthetic.main.sub_task_item_view.view.*

class SubTaskUserAdapter : RecyclerView.Adapter<SubTaskUserAdapter.SubTaskUser>(),
        BindableAdapter<ViewsubtasksItem>{

    var subTaskListByUser = listOf<ViewsubtasksItem>()

    override fun setData(items: List<ViewsubtasksItem>) {
        subTaskListByUser = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Int) {
        notifyItemChanged(positions)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskUser {
        val inflater = LayoutInflater.from(parent.context)
        return SubTaskUserAdapter.SubTaskUser(inflater.inflate(R.layout.sub_task_item_view_by_user, parent, false))
    }

    override fun getItemCount(): Int {
        return subTaskListByUser.size
    }

    override fun onBindViewHolder(holder: SubTaskUser, position: Int) {
        holder.projectIdUser.text = "ProjectId: " + subTaskListByUser[position].projectid
        holder.taskIdUser.text = "TaskId: " + subTaskListByUser[position].taskid
        holder.subTaskIdUser.text = "UserId: " + subTaskListByUser[position].subtaskid
    }


    class SubTaskUser(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var projectIdUser = itemView.tvSubTaskName
        var taskIdUser = itemView.tvSubTaskStatus
        var subTaskIdUser = itemView.tvSubTaskDescription
    }
}