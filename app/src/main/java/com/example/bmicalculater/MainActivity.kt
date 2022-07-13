package com.example.bmicalculater

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

@Suppress("ControlFlowWithEmptyBody")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calButton = findViewById<Button>(R.id.btnCalculate)
        calButton.setOnClickListener() {
            // put height and weight values in string format
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
            // validate the bmi before calculate the result
            if (validateInput(weight, height)) {

                //gets results in two decimal
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }
    // to display the result of the code part,adding the value as parameter
    private fun validateInput(weight : String?, height : String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "height is empty",Toast.LENGTH_LONG).show()
                return false
        }
            else ->{
                return true

            }            }


    }
    // to display the result of the code part,adding the value as parameter bmi which the type is float
    private fun displayResult(bmi:Float){
        val resultIndex= findViewById<TextView>(R.id.tvindex)
        val resultDescription= findViewById<TextView>(R.id.tvResult)
        val info= findViewById<TextView>(R.id.tvinfo)
        // lets display the bmi value
        resultIndex.text = bmi.toString()
        info.text = "(normal range is 18.5 - 24.9)"
        var resultText = ""
        var color = 0

        when {
            bmi < 18.50 ->{
                resultText = "Underweight"
                color= R.color.under_weight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color= R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText = "overWeight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultText = "obese"
                color = R.color.obese
            }
        }
// change the color of result description
        resultDescription.setTextColor(ContextCompat.getColor(this
        , color))
        resultDescription.text = resultText
    }
}