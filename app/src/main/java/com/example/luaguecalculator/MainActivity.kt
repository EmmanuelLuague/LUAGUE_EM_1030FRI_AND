package com.example.luaguecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etNumber1 = findViewById(R.id.etNumber1)
        etNumber2 = findViewById(R.id.etNumber2)
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnAdd).setOnClickListener { calculate("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { calculate("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { calculate("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { calculate("/") }
    }

    private fun calculate(operation: String) {
        val num1 = etNumber1.text.toString().toDoubleOrNull()
        val num2 = etNumber2.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> {
                    if (num2 != 0.0) num1 / num2 else null
                }
                else -> null
            }

            tvResult.text = if (result != null) {
                "Result: %.2f".format(result)
            } else {
                "Error: Division by zero"
            }
        } else {
            tvResult.text = "Error: Invalid input"
        }
    }
}
