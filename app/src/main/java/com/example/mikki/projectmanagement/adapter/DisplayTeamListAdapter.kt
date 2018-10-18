package com.example.mikki.projectmanagement.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.EmployeesItem
import kotlinx.android.synthetic.main.item_name_tag.view.*

class DisplayTeamListAdapter: RecyclerView.Adapter<DisplayTeamListAdapter.ViewHolder>(),
        BindableAdapter<EmployeesItem> {

    var employeeList = mutableListOf<EmployeesItem>()
    lateinit var listener: OnItemClickListener
    interface OnItemClickListener {
        fun onClick(view: View, data: EmployeesItem)
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
        return ViewHolder(inflater.inflate(R.layout.item_name_tag_no_add_btn, parent, false))
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var employee = employeeList[position]
        holder.employeeId.text = "Id: " + employee.empid
        holder.employeeName.text = employee.empfirstname + " " + employee.emplastname

        var char1 = employee.empfirstname?.firstOrNull()
        var char2 = employee.emplastname?.firstOrNull()
        Log.d("MikkiTeam", "first char: " + char1
        +"sec first" + char2)
        holder.itemView.setOnClickListener{
            listener.onClick(it, employee )
        }
        //holder.name_tag.text = char1 + char2


    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var employeeId = itemView.tv_user_id
        var employeeName = itemView.tv_user_name
        var name_tag = itemView.icon_name_tag
    }

}