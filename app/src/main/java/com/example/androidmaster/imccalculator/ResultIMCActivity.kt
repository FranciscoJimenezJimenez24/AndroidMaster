package com.example.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result=intent.extras?.getDouble(IMC_KEY) ?:-1.0
        initComponents()
        initUI(result)
    }

    private fun initUI(result: Double) {
        tvIMC.text=result.toString()
        when(result){
            in 0.00 .. 18.50 -> {
                tvResult.text="Low"
                tvDescription.text="You are suboptimal for your weight and height"
            }
            in 18.50 .. 24.99 -> {
                tvResult.text="Normal"
                tvDescription.text="You are optimal for your weight and height"
            }
            in 25.00 .. 29.99 -> {
                tvResult.text="OverWeight"
                tvDescription.text="You are above optimum for your weight and height"
            }
            in 30.00 .. 99.99 -> {
                tvResult.text="Obesity"
                tvDescription.text="You are way above optimum for your weight and height"
            }else ->{
                tvIMC.text="Error"
                tvResult.text="Error"
                tvDescription.text="Error"
            }
        }
    }

    private fun initComponents() {
        tvResult=findViewById(R.id.tvResult)
        tvIMC=findViewById(R.id.tvIMC)
        tvDescription=findViewById(R.id.tvDescription)
    }
}