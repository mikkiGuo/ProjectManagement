package com.example.mikki.projectmanagement.view.task

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.BR
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.databinding.ItemTaskmemberLayoutBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import org.jetbrains.anko.AnkoLogger

class TaskMemberRecyclerAdapter(val context: Context, var viewModel: TaskViewModel):
        RecyclerView.Adapter<TaskMemberRecyclerAdapter.MyViewHolder>() {

    private val ninntag = AnkoLogger("ninntag")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskMemberRecyclerAdapter.MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskmemberLayoutBinding>(
                LayoutInflater.from(parent.context), R.layout.item_taskmember_layout, parent, false)

        return MyViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return viewModel.taskMemberList.size
    }

    override fun onBindViewHolder(holder: TaskMemberRecyclerAdapter.MyViewHolder, position: Int) {
        var memberItem = viewModel.taskMemberList.get(position)
        holder.bind(memberItem)

        holder.itemView.setOnClickListener {
            if (!memberItem.memberdetails!!.userfirstname.equals("None")) {
                var dialogFragment = MemberDetailsDialogFragment()

                var bundle = Bundle()
                bundle.putParcelable("memberdetails", memberItem)
                dialogFragment.arguments = bundle

                dialogFragment.show((context as Activity).fragmentManager, "dialogFragment")
            }
        }
    }

    class MyViewHolder(itemView: View, val binding: ItemTaskmemberLayoutBinding): RecyclerView.ViewHolder(itemView) {

        private val ninntag = AnkoLogger("ninntag")

        fun bind(memberItem: TaskMemberItem) {
            binding.setVariable(BR.member, memberItem)
            binding.setVariable(BR.space, " ")
        }
    }
}