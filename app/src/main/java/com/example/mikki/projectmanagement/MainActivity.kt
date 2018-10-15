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

class MainActivity : AppCompatActivity(), IDataManager.OnAdminTaskListListener {

    var adminTaskList: ArrayList<ProjectAdminTaskItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iDataManager:IDataManager = DataManager()
        /*var p = ProjectsItem("ecomm", "1"
                , "xyz", "2018-04-03","2018-04-15")*/
        val p = ProjectsItem()
        p.projectname = "ecomm"
        p.endstart = "2018-04-15"
        p.projectdesc = "xyz"
        p.projectstatus = "1"
        p.startdate = "2018-04-03"

        iDataManager.storeNewProjectToServer(p)
        iDataManager.getProjectList()

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val task = ProjectAdminTaskItem()
        task.projectid = "30"
        task.taskname = "mytask"
        task.taskdesc = "my description"

        //iDataManager.createTask(task)
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
