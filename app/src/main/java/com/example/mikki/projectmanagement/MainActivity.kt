package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.mikki.projectmanagement.view.subtask.CreateSubTaskFragment
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import com.example.mikki.projectmanagement.view.task.CreateTaskFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.Snackbar
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import kotlinx.android.synthetic.main.floating_button.*

class MainActivity : AppCompatActivity() {

    private val OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.projects -> {
                val fragment = ProjectListFragment()
                fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.mytasks -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.menu -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_taskList.setOnClickListener {
            var fragment = TaskListFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        bt_createTask.setOnClickListener {
            var fragment = CreateTaskFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        bt_projectList.setOnClickListener {
            val fragment = ProjectListFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        bt_createProject.setOnClickListener {
            val fragment = CreateProjectFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
        }

        bt_createSubTask.setOnClickListener {
            val createSubTaskFrag = CreateSubTaskFragment()
            fragmentManager.beginTransaction().add(R.id.mainActivity, createSubTaskFrag)
                    .addToBackStack(null).commit()
        }

        bt_subTaskList.setOnClickListener {
            val subTaskListFrag = SubTaskFragmentList()
            fragmentManager.beginTransaction().add(R.id.mainActivity, subTaskListFrag)
                    .addToBackStack(null).commit()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
    }

}
