
package com.example.mikki.projectmanagement.project.projectlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectsItem
/*
class ProjectListAdapter: RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    fun setData(items:List<ProjectsItem>){
        productList = items
        notifyDataSetChanged()
    }

    var productList = listOf<ProjectsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_project, parent, false))

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.projectId?.text = productList.get(position).projectname
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var projectId = itemView.findViewById(R.id.tv_project_id) as TextView
    }
}*/
