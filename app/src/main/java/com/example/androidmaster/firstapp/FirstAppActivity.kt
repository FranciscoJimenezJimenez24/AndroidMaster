package com.example.androidmaster.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidmaster.R
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class FirstAppActivity : AppCompatActivity() {
    //is like a main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        //appcompat is the last library of android
        val btnPressMe= findViewById<AppCompatButton>(R.id.btnPressMe)
        val etName=findViewById<AppCompatEditText>(R.id.etName)



        btnPressMe.setOnClickListener {
            val name=etName.text.toString()
            if (name.isNotEmpty()){
                /*
                    Intent is like an object which has something, like a view
                    and throw the view for android to show to the user
                    Intent is used to navigate between views
                 */
                /*
                    this refers to this activity
                    Where are we want to go?: ResultActivity::class.java
                 */
                val intent= Intent(this,ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                //we open the view
                startActivity(intent)
            }

        }

    }
}