package com.example.studentregisterdemo.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(val dao: Student_Dao) :ViewModel(){

    val student = dao.getallstudent()
    fun InsertStudent (student: Student) = viewModelScope.launch {
       dao.InsertStudent(student)
    }
    fun UpdateStudent  (student:Student) = viewModelScope.launch {
        dao.UpdateStudent(student)
    }

    fun DelateStudent (student: Student) = viewModelScope.launch {
        dao.DelateStudent(student)
    }

}