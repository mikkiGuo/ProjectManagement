package com.example.mikki.projectmanagement.adapter

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
import com.example.mikki.projectmanagement.data.model.TaskMemberItem
import com.example.mikki.projectmanagement.databinding.ItemTaskmemberLayoutBinding
import com.example.mikki.projectmanagement.view.task.MemberDetailsDialogFragment
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import com.example.mikki.projectmanagement.viewmodel.ViewModelSubTask
import org.jetbrains.anko.AnkoLogger

class SubTaskMemberRecyclerAdapter(val context: Context, var viewModel: ViewModelSubTask):
        RecyclerView.Adapter<SubTaskMemberRecyclerAdapter.MyViewHolder>() {

    private val ninntag = AnkoLogger("ninntag")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTaskMemberRecyclerAdapter.MyViewHolder {
        val binding = DataBindingUtil.inflate<ItemTaskmemberLayoutBinding>(
                LayoutInflater.from(parent.context), R.layout.item_taskmember_layout, parent, false)

        return MyViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return viewModel.subTaskListByUser.size
    }

    override fun onBindViewHolder(holder: SubTaskMemberRecyclerAdapter.MyViewHolder, position: Int) {
        var memberItem = viewModel.subTaskListByUser.get(position)
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