/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.calculadora_punto_equilibrio.presentation

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.calculadora_punto_equilibrio.R


class MainActivity : Activity() {

    private lateinit var editTextFixedCosts: EditText
    private lateinit var editTextVariableCosts: EditText
    private lateinit var editTextSellingPrice: EditText
    private lateinit var textViewResult: TextView
    private lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFixedCosts = findViewById(R.id.editTextFixedCosts)
        editTextVariableCosts = findViewById(R.id.editTextVariableCosts)
        editTextSellingPrice = findViewById(R.id.editTextSellingPrice)
        textViewResult = findViewById(R.id.textViewResult)
        buttonCalculate = findViewById(R.id.buttonCalculate)

        buttonCalculate.setOnClickListener {
            calculateBreakEven()
        }
    }

    private fun calculateBreakEven() {
        val fixedCostsInput = editTextFixedCosts.text.toString()
        val variableCostsInput = editTextVariableCosts.text.toString()
        val sellingPriceInput = editTextSellingPrice.text.toString()

        if (fixedCostsInput.isEmpty() || variableCostsInput.isEmpty() || sellingPriceInput.isEmpty()) {
            textViewResult.text = "Llenar todos los campos."
            return
        }

        val fixedCosts = fixedCostsInput.toDouble()
        val variableCosts = variableCostsInput.toDouble()
        val sellingPrice = sellingPriceInput.toDouble()

        if (variableCosts >= sellingPrice) {
            textViewResult.text = "Costo variable mayor o igual al precio de venta."
            return
        }

        val breakEvenPoint = fixedCosts / (sellingPrice - variableCosts)
        val decimals = if (breakEvenPoint % 1 != 0.0) 3 else 0
        val formattedBreakEvenPoint = if (decimals > 0) {
            String.format("%.${decimals}f", breakEvenPoint)
        } else {
            breakEvenPoint.toInt().toString()
        }

        textViewResult.text = "Punto de Equilibrio: \n $formattedBreakEvenPoint"
    }
}