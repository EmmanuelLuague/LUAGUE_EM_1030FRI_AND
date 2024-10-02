package com.example.bottomnavluague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val genderRadioGroup = view.findViewById<RadioGroup>(R.id.genderRadioGroup)
        val termsCheckBox = view.findViewById<CheckBox>(R.id.termsCheckBox)
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        saveButton.setOnClickListener {
            // Handle saving the profile data (e.g., storing in shared preferences)
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val genderId = genderRadioGroup.checkedRadioButtonId
            val gender = view.findViewById<RadioButton>(genderId)?.text.toString()
            val termsAccepted = termsCheckBox.isChecked

            // You can save these values or display a Toast message for now
            Toast.makeText(requireContext(), "Profile saved!", Toast.LENGTH_SHORT).show()
        }
    }
}