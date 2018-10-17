package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import kotlinx.android.synthetic.main.item_btn_name_tag.view.*

class ShowTeammateAdapter: RecyclerView.Adapter<ShowTeammateAdapter.ViewHolder>()
        ,BindableAdapter<EmployeesItem>
{

    lateinit var listener: onItemClickListener
    var employeeList = mutableListOf<EmployeesItem>()

    interface onItemClickListener {
        fun onClick(view: View, employee: EmployeesItem, position: Int)
    }

    override fun setData(items: List<EmployeesItem>) {
        employeeList = items as MutableList<EmployeesItem>
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Int) {
        notifyItemChanged(positions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_btn_name_tag, parent, false))
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var employee = employeeList[position]
        holder.btnNameTag.setOnClickListener{
            listener.onClick(it,employee,position)
        }

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btnNameTag = itemView.btn_name_tag
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        this.listener = listener
    }
}