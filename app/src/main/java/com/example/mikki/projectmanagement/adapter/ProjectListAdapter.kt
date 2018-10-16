
package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectsItem

class ProjectListAdapter: RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectListAdapter: RecyclerView.Adapter<ProjectListAdapter.ViewHolder>(),
        BindableAdapter<ProjectsItem> {


    override fun changedPositions(positions: Int) {

        notifyItemChanged(positions)
        //positions.forEach(this::notifyItemChanged)

    }

    override fun setData(items:List<ProjectsItem>){
        prodjectList = items
        notifyDataSetChanged()
    }

    var prodjectList = listOf<ProjectsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_project, parent, false))

    }

    override fun getItemCount(): Int {
        return prodjectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.projectName?.text = prodjectList[position].projectname
        holder.projectDesc.text = prodjectList[position].projectdesc
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var projectName = itemView.tv_project_name
        var projectDesc = itemView.tv_project_desc
    }

}
