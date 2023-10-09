package com.example.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TasksViewHolder(view: View): RecyclerView.ViewHolder(view)  {
    private val tvTask: TextView =view.findViewById(R.id.tvTask)
    private val cbTask:CheckBox=view.findViewById(R.id.cbTask)

    fun render(task: Task){
        //this condition cross and deselect the task
        if (task.isSelected){
            tvTask.paintFlags=tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            tvTask.paintFlags=tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        tvTask.text=task.name
        cbTask.isChecked=task.isSelected

        val color=when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Personal -> R.color.todo_personal_category
            TaskCategory.Other -> R.color.todo_other_category
        }


        //buttonTintList put a different color to the checkbox
        cbTask.buttonTintList= ColorStateList.valueOf(
            ContextCompat.getColor(tvTask.context, color)
        )

    }

}