package com.example.room.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.room.mvvm.model.TableModel
import com.example.room.mvvm.repository.DataRepository

class AcivityViewModel : ViewModel() {

    var liveDataLogin: LiveData<TableModel>? = null

    fun insertData(context: Context, username: String, password: String) {
       DataRepository.insertData(context, username, password)
    }

    fun getLoginDetails(context: Context, username: String) : LiveData<TableModel>? {
        liveDataLogin = DataRepository.getLoginDetails(context, username)
        return liveDataLogin
    }

}