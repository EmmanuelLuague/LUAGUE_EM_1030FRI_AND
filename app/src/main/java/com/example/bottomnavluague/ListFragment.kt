package com.example.bottomnavluague

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var adapter: CustomAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = view.findViewById(R.id.listView)
        editText = view.findViewById(R.id.editText)
        addButton = view.findViewById(R.id.addButton)

        // Check if initial items are already added to the list
        if (sharedViewModel.listItems.value.isNullOrEmpty()) {
            // Initialize with predefined items if the list is empty
            sharedViewModel.addItem(ListItem(ItemType.CHECKBOX, "Checkbox Item", isChecked = true))
            sharedViewModel.addItem(ListItem(ItemType.TEXTVIEW, "TextView ListItem"))
            sharedViewModel.addItem(ListItem(ItemType.IMAGEVIEW, "ImageView ListItem"))
        }

        adapter = CustomAdapter(requireContext(), sharedViewModel.listItems.value ?: mutableListOf())
        listView.adapter = adapter

        addButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                // Add a text item with the user input
                val newItem = ListItem(ItemType.TEXTVIEW, text = inputText)
                sharedViewModel.addItem(newItem)
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        // Observe changes in the sharedViewModel list items
        sharedViewModel.listItems.observe(viewLifecycleOwner) { updatedItems ->
            adapter.notifyDataSetChanged()
        }
    }
}
