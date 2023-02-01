package com.example.room.mvvm.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room.mvvm.R
import com.example.room.mvvm.utils.ApplicationUtility.graphX
import com.example.room.mvvm.utils.ApplicationUtility.graphY
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_day.*

class DayFragment : Fragment() {

    lateinit var x: ArrayList<Entry>
    lateinit var y: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        x = ArrayList()
        y = ArrayList()
        dayChart.setDrawGridBackground(false)
        dayChart.setDescription("Steps")
        dayChart.setTouchEnabled(true)
        dayChart.isDragEnabled = true
        dayChart.setScaleEnabled(true)
        dayChart.setPinchZoom(true)
        dayChart.xAxis.textSize = 14f
        dayChart.axisLeft.textSize = 14f
        dayChart.animateY(1000)
        val xl = dayChart.xAxis
        xl.setAvoidFirstLastClipping(true)
        xl.position = XAxis.XAxisPosition.BOTTOM
        /*val leftAxis = mChart.axisLeft
        leftAxis.isInverted = true*/
        val rightAxis = dayChart.axisRight
        rightAxis.isEnabled = false
        val l: Legend = dayChart.getLegend()
        l.form = Legend.LegendForm.LINE
        getDayList()
    }

    private fun getDayList (){
        val x1 = graphX
        val y1 = graphY
        val data1 = x1.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val data2 = y1.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        /*Loop for day graph as per input data*/
        try {
            Toast.makeText(context, graphX, Toast.LENGTH_SHORT).show()
            for (i in data1.indices) {
                x.add(Entry(data1[i].toInt().toFloat(), i))
                y.add(data2[i]+":00")
                //Log.e("data", data[i].toString())
            }
        }catch (ex:Exception){
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        /*Static test data for line graph*/
        /*x.add(Entry(5F,0))
        x.add(Entry(9F,1))
        x.add(Entry(17F,2))
        x.add(Entry(5F,3))
        x.add(Entry(9F,4))
        x.add(Entry(17F,5))
        x.add(Entry(5F,6))
        x.add(Entry(9F,7))
        x.add(Entry(17F,8))
        x.add(Entry(5F,9))
        x.add(Entry(9F,10))
        x.add(Entry(17F,11))
        y.add("5 AM")
        y.add("6 AM")
        y.add("7 AM")
        y.add("8 AM")
        y.add("9 AM")
        y.add("10 AM")
        y.add("12 PM")
        y.add("01 PM")
        y.add("02 PM")
        y.add("03 PM")
        y.add("04 PM")
        y.add("05 PM")*/

        val set1 = LineDataSet(x, "Day Activity")
        set1.isHighlightEnabled = true
        set1.color = Color.BLUE
        //set1.lineWidth = 1f
        //set1.circleRadius = 3f
        //et1.setCircleColor(Color.GREEN)
        //set1.setDrawHighlightIndicators(true)
        //set1.highLightColor = Color.RED
        set1.valueTextSize = 10.5f
        set1.valueTextColor = Color.DKGRAY
        val data = LineData(y, set1)
        dayChart.data = data
        dayChart.invalidate()
    }
}