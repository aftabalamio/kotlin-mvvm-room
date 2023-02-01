package com.example.room.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.room.mvvm.R
import com.example.room.mvvm.adapter.ViewPagerAdapter
import com.example.room.mvvm.fragment.DayFragment
import com.example.room.mvvm.fragment.WeekFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /*Adding 2 fragment for day and week*/
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DayFragment(), "Day")
        adapter.addFragment(WeekFragment(), "Week")
        viewPager.adapter = adapter
        tbLayout.setupWithViewPager(viewPager)
    }
}