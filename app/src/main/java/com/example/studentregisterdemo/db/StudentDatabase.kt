package com.example.studentregisterdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase :RoomDatabase() {

    abstract fun student_dao():Student_Dao

    companion object{
        private var INSTANCE:StudentDatabase?=null
        fun getinstance(context: Context):StudentDatabase{
            var instance = INSTANCE
            if(instance==null)
            {
                instance=Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "student_db_database"
                )
                    .build()
            }
            return instance
        }
    }



    }

