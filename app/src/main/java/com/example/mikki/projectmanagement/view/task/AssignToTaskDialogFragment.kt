package com.example.mikki.projectmanagement.view.task

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskItem
import com.example.mikki.projectmanagement.data.model.taskmodel.TaskMemberItem
import com.example.mikki.projectmanagement.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.frag_assign_member.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn

class AssignToTaskDialogFragment: DialogFragment(), IDataManager.OnAssignMemberListener {

    private val ninntag = AnkoLogger("ninntag")

    lateinit var viewModel: TaskViewModel
    lateinit var taskMemberItem: TaskMemberItem
    lateinit var taskItem: TaskItem

    override fun onAttach(context: Context?) {
        viewModel = TaskViewModel(context!!)

        var bundle = arguments
        taskMemberItem = bundle.getParcelable("taskmember")
        taskItem = bundle.getParcelable("taskitem")

        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val width = resources.getDimensionPixelSize(R.dimen.dialogFragmentWidth)
        val height = resources.getDimensionPixelSize(R.dimen.dialogFragmentHeight)

        var dialog = super.onCreateDialog(savedInstanceState)

        dialog.setContentView(R.layout.frag_member_details)
        dialog.window.setLayout(width, height)

        return dialog
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var v = inflater.inflate(R.layout.frag_assign_member, container, false)

        v.tv_assign_cancel.setOnClickListener {
            dialog.dismiss()
        }

        v.tv_assignOK.setOnClickListener {
            taskMemberItem.userid = v.et_assignID.text.toString()
            viewModel.assignMemberToTask(this, taskMemberItem)
        }

        return v
    }

    override fun assignMember(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
        activity.fragmentManager.popBackStack()

        var fragment = TaskDetailsFragment()

        var bundle = Bundle()
        bundle.putParcelable("taskitem", taskItem)
        fragment.arguments = bundle

        dialog.dismiss()

        fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
    }
}