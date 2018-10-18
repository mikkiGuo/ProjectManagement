package com.example.mikki.projectmanagement

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.NavigationMenu
import android.support.design.widget.BottomNavigationView
import com.example.mikki.projectmanagement.view.subtask.CreateSubTaskFragment
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import com.example.mikki.projectmanagement.view.task.CreateTaskFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.Snackbar
import android.view.MenuItem
import android.view.View
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.view.login.LoginActivity
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import io.github.yavski.fabspeeddial.FabSpeedDial
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter
import com.example.mikki.projectmanagement.view.team.EmployeeListFragment
import kotlinx.android.synthetic.main.floating_button.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fabSpeedDial = findViewById<View>(R.id.fab_speeddial) as FabSpeedDial

        if (BuildConfig.FLAVOR.equals("developer")) {
            fabSpeedDial.visibility = View.GONE
        }

        fabSpeedDial.setMenuListener(object : SimpleMenuListenerAdapter() {
            override fun onMenuItemSelected(menuItem: MenuItem?): Boolean {

                if (menuItem!!.itemId == R.id.createProject) {
                    val fragment = CreateProjectFragment()
                    fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
                } else if (menuItem.itemId == R.id.createTask) {
                    var fragment = CreateTaskFragment()
                    fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
                } else {
                    val createSubTaskFrag = CreateSubTaskFragment()
                    fragmentManager.beginTransaction().add(R.id.mainActivity, createSubTaskFrag).addToBackStack(null).commit()
                }

                return super.onMenuItemSelected(menuItem)
            }
        })

        (bottom_navigation as BottomNavigationView).setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.projects -> {
                    val fragment = ProjectListFragment()
                    fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
                    true
                }
                R.id.employeeList -> {
                    val subTaskListFrag = EmployeeListFragment()
                    fragmentManager.beginTransaction().add(R.id.mainActivity, subTaskListFrag)
                            .addToBackStack(null).commit()
                    true
                }
                R.id.logout -> {
                    startActivity(intentFor<LoginActivity>())
                    finish()
                    true
                }
            }
            false
        }
    }

}
