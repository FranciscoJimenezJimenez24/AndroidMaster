package com.example.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import java.util.Locale.Category

/*
    This is the adapter
 */
class CategoriesAdapter(private var categories: List<TaskCategory>) :
    RecyclerView.Adapter<CategoriesViewHolder>() {
    /*
        This method creates a visual view
        This view goes to the class CategoriesViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        /*
            This is a inflate of views
            "inflate" inflate a view
         */
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    /*
        This method send the information to onCreateViewHolder to create the view
     */
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position])
    }

    /*
        This implements show the size of a list, which show the number of views
    */
    override fun getItemCount() = categories.size
}