package com.example.studentregisterdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Student_Dao {
    @Insert

    suspend fun InsertStudent(student: Student)

    @Update

    suspend fun UpdateStudent(student: Student)

    @Delete

    suspend fun DelateStudent(student: Student)

    @Query("SELECT * FROM student_data_table")

    fun getallstudent():LiveData<List<Student>>
}