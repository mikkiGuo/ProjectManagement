package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.EmployeesItem

class EmployeeListAdapter: RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>(),
        BindableAdapter<EmployeesItem> {

    var employeeList = mutableListOf<EmployeesItem>()

    override fun setData(items: List<EmployeesItem>) {
        employeeList = items as MutableList<EmployeesItem>
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Int) {
        notifyItemChanged(positions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_name_tag, parent, false))
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }


    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }
}