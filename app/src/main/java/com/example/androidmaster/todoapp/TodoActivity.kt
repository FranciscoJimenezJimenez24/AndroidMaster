package com.example.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R
import com.example.androidmaster.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {
    //has been imported TaskCategory
    private val categories=listOf(
        Business,
        Personal,
        Other
    )

    private val tasks= mutableListOf(
        Task("TestBusiness", Business),
        Task("TestPersona", Personal),
        Task("TestOther", Other)
    )

    private lateinit var rvCategories:RecyclerView
    private lateinit var categoriesAdapter:CategoriesAdapter

    private lateinit var rvTasks:RecyclerView
    private lateinit var taskAdapter:TaskAdapter

    private lateinit var fabAddTask:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        InitComponent()
        InitUI()
        InitListeners()
    }

    private fun InitListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    //This method shows the dialog
    private fun showDialog(){
        val dialog=Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask: Button =dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText =dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup =dialog.findViewById(R.id.rgCategories)
        //checkedRadioButtonId select the RadioButton which is true or selected
        btnAddTask.setOnClickListener {
            val currentTask=etTask.text.toString()
            //This conditions block the button if we don't put nothing
            if (currentTask.isNotEmpty()){
                val selectedId=rgCategories.checkedRadioButtonId
                val selectedRadioButton:RadioButton=rgCategories.findViewById(selectedId)
                val currentCategory:TaskCategory=when(selectedRadioButton.text){
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else ->Other
                }

                tasks.add(Task(etTask.text.toString(),currentCategory))
                updateTask()
                dialog.hide()
            }


        }

        dialog.show()
    }

    private fun InitComponent() {
        rvCategories=findViewById(R.id.rvCategories)
        rvTasks=findViewById(R.id.rvTasks)
        fabAddTask=findViewById(R.id.fabAddTask)
    }
    /*
        For a RecyclerView to work, consist of two parts:
        Adapter: is the class which connect all the information with the RecyclerView
        ViewHolder: is the class which show thatQ
        layoutManager is responsible that the view could be horizontal or vertical
     */
    private fun InitUI() {
        categoriesAdapter=CategoriesAdapter(categories) {position->updateCategories(position)}
        rvCategories.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter=categoriesAdapter

        taskAdapter= TaskAdapter(tasks) {
                position -> onItemSelected(position)
        }
        //Default is vertical
        rvTasks.layoutManager=LinearLayoutManager(this)
        rvTasks.adapter=taskAdapter
    }
    /*
        This method modify the item to selected to not selected and otherwise
     */
    private fun onItemSelected(position:Int){
        tasks[position].isSelected=!tasks[position].isSelected
        updateTask()
    }
    //notifyItemChanged detect that a item is modified
    private fun updateCategories(position: Int){
        categories[position].isSelected=!categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()

    }

    //This method warning to the Adapter that there are new items
    private fun updateTask(){
        val selectedCategories:List<TaskCategory> =categories.filter { it.isSelected }
        val newTasks=tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.tasks=newTasks
        //notifyDataSetChanged update the data
        taskAdapter.notifyDataSetChanged()
    }
}