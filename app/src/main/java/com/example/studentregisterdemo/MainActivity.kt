package com.example.studentregisterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregisterdemo.db.Student
import com.example.studentregisterdemo.db.StudentDatabase
import com.example.studentregisterdemo.db.StudentViewModel
import com.example.studentregisterdemo.db.StudentViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var name:EditText
    private lateinit var email:EditText
    private lateinit var savebutton:Button
    private lateinit var clearbutton:Button

    private lateinit var studentRecyclerview :RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter

    private lateinit var viewModel: StudentViewModel
    private lateinit var selectedstudent:Student
    private var iscliklistner = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name=findViewById(R.id.etname)
        email=findViewById(R.id.etemail)
        savebutton=findViewById(R.id.addbtn)
        clearbutton=findViewById(R.id.delbtn)
        studentRecyclerview=findViewById(R.id.recview)


        val dao = StudentDatabase.getinstance(application).student_dao()
        val factory = StudentViewModelFactory(dao)
        viewModel= ViewModelProvider(this,factory).get(StudentViewModel::class.java)

      savebutton.setOnClickListener {

          if(iscliklistner){
             updatestudent()
             clearstudentdata()
          }
          else{
              savestudentdata()
              clearstudentdata()
          }

      }
        clearbutton.setOnClickListener {
            if(iscliklistner){
                delatestudent()
                clearstudentdata()
            }
            else{
                clearstudentdata()
            }

        }

        initrecyclerview()
    }
    private fun savestudentdata(){
        viewModel.InsertStudent(
            Student(
                0,
                name.text.toString(),
               email.text.toString()
            )
        )
    }
    private fun clearstudentdata(){
        name.setText("")
        email.setText("")
    }

    private fun updatestudent(){
        viewModel.UpdateStudent(
            Student(
                selectedstudent.id,
                name.text.toString(),
                email.text.toString()
            )
        )
        savebutton.text = "Save"
        clearbutton.text = "Clear"
        iscliklistner = false

    }

    private fun delatestudent(){
        viewModel.DelateStudent(
            Student(
            selectedstudent.id,
                name.text.toString(),
                email.text.toString()
        )
        )
        savebutton.text = "Save"
        clearbutton.text = "Clear"
        iscliklistner = false
    }
    private fun initrecyclerview(){
        studentRecyclerview.layoutManager=LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter{
            listitem :Student->cliklistner(listitem)
        }

        studentRecyclerview.adapter=adapter
        displaylist()
    }

    private fun displaylist(){
       viewModel.student.observe(this,{
           adapter.setlist(it)
           adapter.notifyDataSetChanged()

       })
    }

private fun cliklistner(student: Student){
    /*Toast.makeText(
        this,"Student Name is ${student.name}",Toast.LENGTH_LONG
    ).show()
    */

    selectedstudent = student
    savebutton.text = "Update"
    clearbutton.text = "Delate"
    iscliklistner = true

    name.setText(selectedstudent.name)
    email.setText(selectedstudent.email)

}
}