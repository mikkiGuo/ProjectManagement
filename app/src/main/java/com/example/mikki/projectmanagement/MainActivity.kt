package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectSubTaskItem
import com.example.mikki.projectmanagement.data.model.ProjectsItem
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

        var subTask = ProjectSubTaskItem()
        subTask.projectid = "27"
        subTask.taskid = "1"
        subTask.subtaskname = "Best SubTask"
        subTask.subtaskdesc = "This is SubTask"
        subTask.subtaskstatus = "1"
        subTask.startdate = "2018-01-22"
        subTask.endstart = "2018-03-22"

        //iDataManager.storeNewProjectToServer(p)

        //iDataManager.getProjectList()
        iDataManager.storeNewSubTaskToServer(subTask)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
