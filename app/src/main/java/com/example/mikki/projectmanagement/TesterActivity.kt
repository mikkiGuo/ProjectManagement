package com.example.mikki.projectmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.mikki.projectmanagement.view.subtask.SubTaskFragmentList
import kotlinx.android.synthetic.main.activity_tester.*

class TesterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tester)

        val subTaskListFrag = SubTaskFragmentList()

        btSubTaskList.setOnClickListener {
            fragmentManager.beginTransaction()
                    //.add(R.id.fragmentLayout, fragment)
                    .add(R.id.fragmentLayout, subTaskListFrag)
                    .addToBackStack(null)
                    .commit()
        }
    }
}
