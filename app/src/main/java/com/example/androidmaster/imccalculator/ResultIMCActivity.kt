package com.example.androidmaster.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculateIMC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        //with onBackPressed(), we can back to the other interface
        btnRecalculateIMC.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResult.text = "Low"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.low_weight))
                tvDescription.text = "You are suboptimal for your weight and height"
            }

            in 18.50..24.99 -> {
                tvResult.text = "Normal"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvDescription.text = "You are optimal for your weight and height"
            }

            in 25.00..29.99 -> {
                tvResult.text = "OverWeight"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
                tvDescription.text = "You are above optimum for your weight and height"
            }

            in 30.00..99.99 -> {
                tvResult.text = "Obesity"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = "You are way above optimum for your weight and height"
            }

            else -> {
                tvIMC.text = "Error"
                tvResult.text = "Error"
                tvDescription.text = "Error"
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
            }
        }
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculateIMC = findViewById(R.id.btnRecalculateIMC)
    }
}