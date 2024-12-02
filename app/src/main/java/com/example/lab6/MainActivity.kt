package com.example.lab6

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Поля для введення даних
        val inputNominalPower = findViewById<EditText>(R.id.inputNominalPower)
        val inputEfficiency = findViewById<EditText>(R.id.inputEfficiency)
        val inputCosPhi = findViewById<EditText>(R.id.inputCosPhi)
        val inputQuantity = findViewById<EditText>(R.id.inputQuantity)
        val inputUsageFactor = findViewById<EditText>(R.id.inputUsageFactor)
        val inputReactiveFactor = findViewById<EditText>(R.id.inputReactiveFactor)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Обробка натискання кнопки "Розрахувати"
        calculateButton.setOnClickListener {
            try {
                val nominalPower = inputNominalPower.text.toString().toDouble()
                val efficiency = inputEfficiency.text.toString().toDouble()
                val cosPhi = inputCosPhi.text.toString().toDouble()
                val quantity = inputQuantity.text.toString().toInt()
                val usageFactor = inputUsageFactor.text.toString().toDouble()
                val reactiveFactor = inputReactiveFactor.text.toString().toDouble()

                // Розрахунки
                val totalPower = nominalPower * quantity * usageFactor
                val reactivePower = totalPower * reactiveFactor
                val apparentPower = Math.sqrt(totalPower * totalPower + reactivePower * reactivePower)
                val current = (totalPower * 1000) / (efficiency * cosPhi * 380 * Math.sqrt(3.0))

                // Виведення результатів
                val result = """
                    Активне навантаження: $totalPower кВт
                    Реактивне навантаження: $reactivePower квар
                    Повна потужність: $apparentPower кВА
                    Розрахунковий струм: ${"%.2f".format(current)} А
                """.trimIndent()

                resultTextView.text = result
            } catch (e: Exception) {
                resultTextView.text = "Помилка вводу даних!"
            }
        }
    }
}


