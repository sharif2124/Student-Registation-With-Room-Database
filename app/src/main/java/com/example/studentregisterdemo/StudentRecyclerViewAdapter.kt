package com.example.studentregisterdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregisterdemo.db.Student

class StudentRecyclerViewAdapter(
    private val clickListner:(Student)->Unit
):RecyclerView.Adapter<StudentViwholder>(){

    private val studentlist =  ArrayList<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViwholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listofstudent = layoutInflater.inflate(R.layout.single_raw,parent,false)
        return StudentViwholder(listofstudent)
    }

    override fun onBindViewHolder(holder: StudentViwholder, position: Int) {
       holder.databind(studentlist[position],clickListner)
    }

    override fun getItemCount(): Int {
        return studentlist.size
    }

    fun setlist(students:List<Student>)
    {
        studentlist.clear()
        studentlist.addAll(students)
    }
}
class StudentViwholder(private val view:View):RecyclerView.ViewHolder(view){

    fun databind(student: Student,clickListner:(Student)->Unit){
        val tvname = view.findViewById<TextView>(R.id.tvname)
        val tvemail =view.findViewById<TextView>(R.id.tvemail)

        tvname.text = student.name
        tvemail.text = student.email

        view.setOnClickListener {
           clickListner(student)
        }

    }

}