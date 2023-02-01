package com.example.room.mvvm.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.room.mvvm.R
import com.example.room.mvvm.utils.ApplicationUtility
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.fragment_day.*
import kotlinx.android.synthetic.main.fragment_week.*


class WeekFragment : Fragment() {

    lateinit var x: ArrayList<BarEntry>
    lateinit var y: ArrayList<String>
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_week, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        x = ArrayList()
        y = ArrayList()
        weekChart.setDrawGridBackground(false)
        weekChart.setDescription("Steps")
        weekChart.setTouchEnabled(true)
        weekChart.isDragEnabled = true
        weekChart.setScaleEnabled(true)
        weekChart.setPinchZoom(true)
        weekChart.xAxis.textSize = 14f
        weekChart.axisLeft.textSize = 14f
        weekChart.animateY(1000)
        val xl = weekChart.xAxis
        xl.setAvoidFirstLastClipping(true)
        xl.position = XAxis.XAxisPosition.BOTTOM
        /*val leftAxis = mChart.axisLeft
        leftAxis.isInverted = true*/
        val rightAxis = weekChart.axisRight
        rightAxis.isEnabled = false
        val l: Legend = weekChart.getLegend()
        l.form = Legend.LegendForm.LINE
        getWeekList()
    }

    private fun getWeekList (){
        val x1 = ApplicationUtility.graphX
        val days = arrayOf("Mon", "Tue", "Wed", "Thu", "Fir", "Sat", "Sun")
        val steps = x1.replace(" ", "")
        var count = 0
        val sumOfStr = steps.sumByDouble{
            count++
            it.toString().toDouble()
        }

        /*Loop for week graph as per day data*/
        try {
            Toast.makeText(context, sumOfStr.toString(), Toast.LENGTH_SHORT).show()
            for (i in days.indices) {
                x.add(BarEntry(sumOfStr.toInt().toFloat(), i))
                y.add(days[i])
                //Log.e("data", days[i].toString())
            }
        }catch (ex:Exception){
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }

        /*Static data for bar graph*/
        /*x.add(BarEntry(5F,0))
        x.add(BarEntry(9F,1))
        x.add(BarEntry(17F,2))
        x.add(BarEntry(5F,3))
        x.add(BarEntry(9F,4))
        x.add(BarEntry(17F,5))
        x.add(BarEntry(5F,0))
        y.add("Mon")
        y.add("Tue")
        y.add("Wed")
        y.add("Thu")
        y.add("Fri")
        y.add("Sat")
        y.add("Sun")*/

        val set1 = BarDataSet(x, "Week Activity")
        set1.isHighlightEnabled = true
        set1.color = Color.BLUE
        //set1.lineWidth = 1f
        //set1.circleRadius = 3f
        //set1.setCircleColor(Color.GREEN)
        //set1.setDrawHighlightIndicators(true)
        //set1.highLightColor = Color.RED
        set1.valueTextSize = 10.5f
        set1.valueTextColor = Color.DKGRAY
        val data = BarData(y, set1)
        weekChart.data = data
        weekChart.invalidate()
    }
}