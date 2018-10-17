package com.example.mikki.projectmanagement.adapter

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.view.subtask.EditSubTaskFragment
import kotlinx.android.synthetic.main.sub_task_item_view.view.*

class SubTaskAdapter(context: Context) : RecyclerView.Adapter<SubTaskAdapter.SubTaskHolder>(),
        BindableAdapter<ProjectSubTaskItem> {

    var subTaskList = listOf<ProjectSubTaskItem>()

    override fun setData(items: List<ProjectSubTaskItem>) {
        subTaskList = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Int) {
        notifyItemChanged(positions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SubTaskHolder(inflater.inflate(R.layout.sub_task_item_view, parent, false))
    }

    override fun getItemCount(): Int {
      return subTaskList.size
    }

    override fun onBindViewHolder(holder: SubTaskHolder, position: Int) {

        holder.subTaskName.text = "Name: " + subTaskList[position].subtaskname
        holder.subTaskStatus.text = "Status: " + subTaskList[position].subtaskstatus
        holder.subTaskDescription.text = "Description: " + subTaskList[position].subtaskdesc
        holder.subTaskStartDate.text = "Date: " + subTaskList[position].startdate + " - " + subTaskList[position].endstart


        holder.itemView.setOnClickListener {view ->


            val starter :AppCompatActivity = view.context as AppCompatActivity
            val editFrag = EditSubTaskFragment()
            val args = Bundle()
            args.putParcelable("subtask", subTaskList[position])
            editFrag.arguments = args
            starter.fragmentManager.beginTransaction()
                    //.add(R.id.fragmentLayout, fragment)
                    .replace(R.id.fragmentLayout, editFrag)
                    .addToBackStack(null)
                    .commit()

            //Log.d("data", subTaskList.get(position).toString())
        }
    }

    class SubTaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var subTaskName = itemView.tvSubTaskName
        var subTaskStatus = itemView.tvSubTaskStatus
        var subTaskDescription = itemView.tvSubTaskDescription
        var subTaskStartDate = itemView.tvSubTaskStartDate
        //var subTaskEndDate = itemView.tvSubTaskEndDate

//        fun bind(subTask: ProjectSubTaskItem) {
//            itemView.tvSubTaskName.text = "SubTaskName: ${subTask.subtaskname}"
//        }
    }
}
