
package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectListAdapter: RecyclerView.Adapter<ProjectListAdapter.ViewHolder>(),
        BindableAdapter<ProjectsItem> {

    lateinit var listener: onItemClickListener

    interface onItemClickListener{
        fun onClick(view:View, project:ProjectsItem, position: Int)
    }

    override fun changedPositions(positions: Int) {
        Log.d("mikkiindex", "adapter " + positions)
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
        var project = prodjectList.get(position)
        holder.projectName?.text = project.projectname
        holder.projectDesc.text = project.projectdesc

        //holder.bind(project)
        holder.itemView.setOnClickListener{
            listener.onClick(it,project,position)
        }

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var projectName = itemView.tv_project_name
        var projectDesc = itemView.tv_project_desc
        /*fun bind(projectsItem: ProjectsItem){
            var projectName = itemView.tv_project_name
            var projectDesc = itemView.tv_project_desc
        }*/

    }

}
