package com.example.mikki.projectmanagement.view

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mikki.projectmanagement.R

class ContentFloatingBtnFragment:Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view:View = inflater!!.inflate(R.layout.frag_content_floating_button,
                container, false)

        return view
    }
}