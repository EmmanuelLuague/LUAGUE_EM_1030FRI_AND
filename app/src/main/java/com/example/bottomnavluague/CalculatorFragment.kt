package com.example.bottomnavluague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class CalculatorFragment : Fragment() {

    private lateinit var etNumber1: EditText
    private lateinit var etNumber2: EditText
    private lateinit var tvResult: TextView
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        etNumber1 = view.findViewById(R.id.etNumber1)
        etNumber2 = view.findViewById(R.id.etNumber2)
        tvResult = view.findViewById(R.id.tvResult)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Restore saved result from ViewModel
        sharedViewModel.calculatorResult.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                tvResult.text = "Result: %.2f".format(result)
            }
        }

        view.findViewById<Button>(R.id.btnAdd).setOnClickListener { calculate("+") }
        view.findViewById<Button>(R.id.btnSubtract).setOnClickListener { calculate("-") }
        view.findViewById<Button>(R.id.btnMultiply).setOnClickListener { calculate("*") }
        view.findViewById<Button>(R.id.btnDivide).setOnClickListener { calculate("/") }

        return view
    }

    private fun calculate(operation: String) {
        val num1 = etNumber1.text.toString().toDoubleOrNull()
        val num2 = etNumber2.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else null
                else -> null
            }

            // Save result to ViewModel
            if (result != null) {
                sharedViewModel.calculatorResult.value = result
                tvResult.text = "Result: %.2f".format(result)
            } else {
                tvResult.text = "Error: Division by zero"
            }
        } else {
            tvResult.text = "Error: Invalid input"
        }
    }
}
