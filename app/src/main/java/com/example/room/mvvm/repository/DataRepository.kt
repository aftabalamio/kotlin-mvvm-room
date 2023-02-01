package com.example.room.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.room.mvvm.model.TableModel
import com.example.room.mvvm.room.ActivityDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DataRepository {

    companion object {

        var activityDatabase: ActivityDatabase? = null

        var tableModel: LiveData<TableModel>? = null

        fun initializeDB(context: Context) : ActivityDatabase {
            return ActivityDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            activityDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = TableModel(username, password)
                activityDatabase!!.loginDao().InsertData(loginDetails)
            }

        }

        fun getLoginDetails(context: Context, username: String) : LiveData<TableModel>? {

            activityDatabase = initializeDB(context)

            tableModel = activityDatabase!!.loginDao().getLoginDetails(username)

            return tableModel
        }

    }
}