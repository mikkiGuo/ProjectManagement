package com.example.mikki.projectmanagement.view.task

import android.app.Dialog
import android.app.DialogFragment
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.solver.LinearSystem
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.model.MemberDetails
import com.example.mikki.projectmanagement.data.model.TaskMemberItem
import com.example.mikki.projectmanagement.databinding.FragMemberDetailsBinding
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import org.jetbrains.anko.layoutInflater

class MemberDetailsDialogFragment(): DialogFragment() {

    lateinit var viewModel: TaskViewModel
    lateinit var memberDetails: MemberDetails

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val width = resources.getDimensionPixelSize(R.dimen.dialogFragmentWidth)
        val height = resources.getDimensionPixelSize(R.dimen.dialogFragmentHeight)

        var dialog = super.onCreateDialog(savedInstanceState)

        dialog.setContentView(R.layout.frag_member_details)
        dialog.window.setLayout(width, height)

        return dialog
    }

    override fun onAttach(context: Context?) {
        viewModel = TaskViewModel(context!!)

        var bundle = arguments
        var memberItem = bundle.getParcelable<TaskMemberItem>("memberdetails")
        memberDetails = memberItem.memberdetails!!

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragMemberDetailsBinding>(
                inflater, R.layout.frag_member_details, container, false)

        var v = binding.root

        binding.memberdetails = memberDetails
        binding.executePendingBindings()

        return v
    }
}