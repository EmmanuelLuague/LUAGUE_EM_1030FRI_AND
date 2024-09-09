package com.example.luaguetodolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var editText: EditText
    private lateinit var addButton: Button
    private lateinit var adapter: CustomAdapter
    private val items = mutableListOf<ListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        editText = findViewById(R.id.editText)
        addButton = findViewById(R.id.addButton)

        // Initialize with predefined items
        items.add(ListItem(ItemType.CHECKBOX, isChecked = true))
        items.add(ListItem(ItemType.TEXTVIEW, text = "TextView ListItem"))
        items.add(ListItem(ItemType.IMAGEVIEW))

        adapter = CustomAdapter(this, items)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotEmpty()) {
                // Add a text item with the user input
                items.add(ListItem(ItemType.TEXTVIEW, text = inputText))
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }
    }
}