package com.example.mikki.projectmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.view.subtask.CreateSubTaskFragment
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import com.example.mikki.projectmanagement.view.task.CreateTaskFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import kotlinx.android.synthetic.main.floating_button.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iDataManager:IDataManager = DataManager()
        /*var p = ProjectsItem("ecomm", "1"
                , "xyz", "2018-04-03","2018-04-15")*/
        /*val p = ProjectsItem()
        p.projectname = "ecomm"
        p.endstart = "2018-04-15"
        p.projectdesc = "xyz"
        p.projectstatus = "1"
        p.startdate = "2018-04-03"*/

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
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            val subTaskListFrag = SubTaskFragmentList()
//            val createSubTaskFrag = CreateSubTaskFragment()
//            val intent = Intent(this, TesterActivity::class.java)
//            startActivity(intent)
//            fragmentManager.beginTransaction()
//                    //.add(R.id.fragmentLayout, fragment)
//                    .add(R.id.mainActivity, subTaskListFrag)
//                    .addToBackStack(null)
//                    .commit()
        }
    }

//    override fun getAdminTaskList(adminTaskList: ArrayList<ProjectAdminTaskItem>?) {
//        this.adminTaskList = adminTaskList
//        if (this.adminTaskList != null) {
//            Log.d("ninntag", "fragment getadmintasklist: " + adminTaskList!!.size)
//        }
//        else
//            Log.d("ninntag", "fragment getadmintasklist: null")
//    }



}
