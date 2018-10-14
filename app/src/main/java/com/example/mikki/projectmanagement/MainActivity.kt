package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mikki.projectmanagement.data.DataManager
import com.example.mikki.projectmanagement.data.IDataManager
import com.example.mikki.projectmanagement.data.model.ProjectsItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snack_create_new_project)

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
    }
}
