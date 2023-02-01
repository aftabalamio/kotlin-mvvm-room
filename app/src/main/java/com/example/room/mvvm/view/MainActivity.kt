package com.example.room.mvvm.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.room.mvvm.R
import com.example.room.mvvm.utils.ApplicationUtility.graphX
import com.example.room.mvvm.utils.ApplicationUtility.graphY
import com.example.room.mvvm.viewmodel.AcivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var activityViewModel: AcivityViewModel
    lateinit var context: Context
    lateinit var strXData: String
    lateinit var strYData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity
        activityViewModel = ViewModelProvider(this).get(AcivityViewModel::class.java)

        btnAddLogin.setOnClickListener {
            strXData = txtValue.text.toString().trim()
            strYData = txtTime.text.toString().trim()
            if (strYData.isEmpty()) {
                txtValue.error = "Please enter the data"
            }
            else if (strYData.isEmpty()) {
                txtTime.error = "Please enter the data"
            }
            else {
                activityViewModel.insertData(context, strXData, strYData)
                lblInsertResponse.text = "Inserted Successfully"
            }
        }

        btnReadLogin.setOnClickListener {
            strXData = txtValue.text.toString().trim()
            activityViewModel.getLoginDetails(context, strXData)!!.observe(this, Observer {
                if (it == null) {
                    lblReadResponse.text = "Data Not Found"
                    lblUseraname.text = "- - -"
                    lblPassword.text = "- - -"
                }
                else {
                    lblUseraname.text = it.Username
                    lblPassword.text = it.Password
                    graphX = it.Username
                    graphY = it.Password
                    lblReadResponse.text = "Data Found Successfully"
                }
            })
        }

        lblReadHeading.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}