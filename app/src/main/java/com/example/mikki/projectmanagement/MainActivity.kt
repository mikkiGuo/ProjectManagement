package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.mikki.projectmanagement.data.database.IDbHelper
import com.example.mikki.projectmanagement.data.model.Project
import com.example.mikki.projectmanagement.data.network.INetworkHelper
import com.example.mikki.projectmanagement.data.network.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.floating_button.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.snack_create_new_project)

        /*val networkHelper:INetworkHelper = NetworkHelper()
        var p:Project = Project("ecomm", "1"
                , "xyz", "2018-04-03","2018-04-15")
        networkHelper.storeNewProjectToServer(p)*/

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
    }
}
