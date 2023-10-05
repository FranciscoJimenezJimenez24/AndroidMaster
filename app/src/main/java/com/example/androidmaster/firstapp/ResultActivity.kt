package com.example.androidmaster.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidmaster.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResul=findViewById<TextView>(R.id.tvResult)
        val name:String=intent.extras?.getString("EXTRA_NAME").orEmpty()
        tvResul.text="Hello $name"
    }
}