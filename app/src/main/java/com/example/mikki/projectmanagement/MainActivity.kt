package com.example.mikki.projectmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.NavigationMenu
import android.support.design.widget.BottomNavigationView
import com.example.mikki.projectmanagement.view.subtask.CreateSubTaskFragment
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import com.example.mikki.projectmanagement.view.task.CreateTaskFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.Snackbar
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.mikki.projectmanagement.R
import com.example.mikki.projectmanagement.login.LoginActivity
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import com.example.mikki.projectmanagement.view.task.TaskListFragment
import io.github.yavski.fabspeeddial.FabSpeedDial
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter
import com.example.mikki.projectmanagement.view.team.EmployeeListFragment
import kotlinx.android.synthetic.main.floating_button.*
import kotlinx.android.synthetic.main.frag_chart.view.*
import org.jetbrains.anko.intentFor
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val OnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.projects -> {
                val fragment = ProjectListFragment()
                fragmentManager.beginTransaction().add(R.id.mainActivity, fragment).addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.employeeList -> {
                val subTaskListFrag = EmployeeListFragment()
                fragmentManager.beginTransaction().add(R.id.mainActivity, subTaskListFrag)
                        .addToBackStack(null).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.logout -> {
                startActivity(intentFor<LoginActivity>())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    //private val menulistener = FabSpeedDial.MenuListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fabSpeedDial = findViewById<FabSpeedDial>(R.id.fab_speeddial)
        //fabSpeedDial.setMenuListener(menulistener)

        /*bt_createTask.setOnClickListener {
            var fragment = CreateTaskFragment()
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
        }*/
        var piechart = AnyChart.pie()
        val dataEntryList = ArrayList<DataEntry>()
        dataEntryList.add(ValueDataEntry("Completed", 13))
        dataEntryList.add(ValueDataEntry("In progress", 25))
        dataEntryList.add(ValueDataEntry("New", 3))

        piechart.data(dataEntryList)

        any_chart_view.setChart(piechart)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener)
    }

}
