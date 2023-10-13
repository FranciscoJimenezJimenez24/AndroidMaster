package com.example.androidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidmaster.firstapp.FirstAppActivity
import com.example.androidmaster.imccalculator.ImcCalculatorActivity
import com.example.androidmaster.superheroeapp.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnHelloApp=findViewById<Button>(R.id.btnHelloApp)
        val btnIMCApp=findViewById<Button>(R.id.btnIMCApp)
        val btnTODOapp=findViewById<Button>(R.id.btnTODOapp)
        val btnSuperHero=findViewById<Button>(R.id.btnSuperHeroApp)
        btnHelloApp.setOnClickListener { navigateToHelloApp() }
        btnIMCApp.setOnClickListener { navigateToIMCApp() }
        btnTODOapp.setOnClickListener { navigateToTODOapp() }
        btnSuperHero.setOnClickListener { navigateToSuperHeroApp() }
    }

    private fun navigateToTODOapp() {
        val intent= Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCApp() {
        val intent= Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToHelloApp(){
        val intent= Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroApp() {
        val intent= Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }


}