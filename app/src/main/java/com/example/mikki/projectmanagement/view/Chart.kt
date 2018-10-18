package com.example.mikki.projectmanagement.view

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.mikki.projectmanagement.R
import kotlinx.android.synthetic.main.frag_chart.view.*
import java.util.ArrayList

class Chart:Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view:View = inflater!!.inflate(R.layout.frag_chart, container, false)

        var piechart = AnyChart.pie()
        val dataEntryList = ArrayList<DataEntry>()
        dataEntryList.add(ValueDataEntry("Completed", 13))
        dataEntryList.add(ValueDataEntry("In progress", 25))
        dataEntryList.add(ValueDataEntry("New", 3))

        piechart.data(dataEntryList)

        view.any_chart_view.setChart(piechart)

        return view
    }


}