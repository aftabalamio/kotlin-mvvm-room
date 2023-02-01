package com.example.room.mvvm.room

import android.content.Context
import androidx.room.*
import com.example.room.mvvm.model.TableModel

@Database(entities = arrayOf(TableModel::class), version = 1, exportSchema = false)
abstract class ActivityDatabase : RoomDatabase() {

    abstract fun loginDao() : DAOAccess

    companion object {
        @Volatile
        private var INSTANCE: ActivityDatabase? = null

        fun getDataseClient(context: Context) : ActivityDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, ActivityDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}