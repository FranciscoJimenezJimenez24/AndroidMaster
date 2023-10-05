package com.example.androidmaster.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var currentWeight: Int = 70
    private var currentAge: Int = 44
    private var currentHeight: Double = 1.76

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView

    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView

    private lateinit var btnCalculateIMC: Button

    //we can use everything that is inside
    //is like a static attribute of java
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    //Initialize the components
    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)

        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)

        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)

        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)

        btnCalculateIMC = findViewById(R.id.btnCalculateIMC)
    }


    //This method works when you press a button
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeSex()
            setSexColor()
        }
        viewFemale.setOnClickListener {
            changeSex()
            setSexColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toDouble()
            tvHeight.text = "$currentHeight m"
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight--
            setWeight()
        }
        btnPlusWeight.setOnClickListener {
            currentWeight++
            setWeight()
        }
        btnSubtractAge.setOnClickListener {
            currentAge--
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge++
            setAge()
        }
        btnCalculateIMC.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }

    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        return currentWeight / (currentHeight * currentHeight)
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    //This method change the value of isMaleSelected and isFemaleSelected
    private fun changeSex() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    //This method select the sex
    private fun setSexColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    //This method change the background of the view when we press one of the two buttons
    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReferences = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReferences)
    }

    //This method initialize the interface
    private fun initUI() {
        setSexColor()
        setWeight()
    }
}