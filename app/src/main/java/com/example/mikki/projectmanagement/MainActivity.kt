package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.mikki.projectmanagement.view.project.CreateProjectFragment
import com.example.mikki.projectmanagement.view.project.ProjectListFragment
import kotlinx.android.synthetic.main.floating_button.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val fragment = ProjectListFragment()
        val fragment = CreateProjectFragment()

        fragmentManager.beginTransaction().add(R.id.frame_layout,
                fragment).addToBackStack(null).commit()


        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}
