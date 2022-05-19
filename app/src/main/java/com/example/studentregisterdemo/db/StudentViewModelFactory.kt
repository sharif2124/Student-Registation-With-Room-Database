package com.example.studentregisterdemo.db

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import java.lang.IllegalArgumentException

class StudentViewModelFactory(private val dao: Student_Dao):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(dao)as T
        }
        throw IllegalArgumentException("Unknown View Model class")
        //return super.create(modelClass, extras)
    }

    }


