package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectAdminTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import com.example.mikki.projectmanagement.view.task.CreateTaskFragment
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.Snackbar
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import kotlinx.android.synthetic.main.floating_button.*

class MainActivity : AppCompatActivity(), IDataManager.OnAdminTaskListListener {

    var adminTaskList: ArrayList<ProjectAdminTaskItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iDataManager:IDataManager = DataManager()

        iDataManager.getAdminTaskList(this)
        iDataManager.getUserTaskList("14")

        bt_taskList.setOnClickListener {
            var fragment = TaskListFragment()

            var bundle = Bundle()
            bundle.putParcelableArrayList("tasklist", adminTaskList)
            fragment.arguments = bundle

            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
            Log.d("ninntag", "tasklistfragment transaction begin")
        }

        bt_createTask.setOnClickListener {
            var fragment = CreateTaskFragment()

            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
            Log.d("ninntag", "newtaskfragment transaction begin")

        }

        bt_projectList.setOnClickListener {
            val fragment = ProjectListFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        bt_createProject.setOnClickListener {
            val fragment = CreateProjectFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun getAdminTaskList(adminTaskList: ArrayList<ProjectAdminTaskItem>?) {
        this.adminTaskList = adminTaskList
        if (this.adminTaskList != null) {
            Log.d("ninntag", "fragment getadmintasklist: " + adminTaskList!!.size)
        }
        else
            Log.d("ninntag", "fragment getadmintasklist: null")
    }

}
