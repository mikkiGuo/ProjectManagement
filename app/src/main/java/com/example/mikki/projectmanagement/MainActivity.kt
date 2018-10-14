package com.example.mikki.projectmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
import kotlinx.android.synthetic.main.frag_create_project.*
import kotlinx.android.synthetic.main.snack_create_new_project.*

class MainActivity : AppCompatActivity() {

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

        fun newInstance(): SubTaskFragment {
            return SubTaskFragment()
        }




//        btCreateSubTask.setOnClickListener {
//                  supportFragmentManager.beginTransaction()
//                .add(R.id.fragmentLayout,newInstance()).commit()
//        }

        val subTask = ProjectSubTaskItem()

        subTask.projectid = "27"
        subTask.taskid = "1"
        subTask.subtaskname = "Last Best SubTask"
        subTask.subtaskstatus = "1"
        subTask.subtaskdesc = "This is Sparta"
        subTask.startdate = "2018-10-14"
        subTask.endstart = "2018-10-18"

        Log.d("Subtask values: ", subTask.toString())

        iDataManager.storeNewSubTaskToServer(subTask)
        iDataManager.getSubTaskList()

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }
}
