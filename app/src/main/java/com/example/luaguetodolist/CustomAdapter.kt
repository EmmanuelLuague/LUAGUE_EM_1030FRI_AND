package com.example.luaguetodolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog

class CustomAdapter(
    private val context: Context,
    private val items: MutableList<ListItem>
) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
        val textView = view.findViewById<TextView>(R.id.textViewItem)
        val imageView = view.findViewById<ImageView>(R.id.imageView)

        val item = items[position]

        // Set visibility based on item type and populate TextView with item text
        when (item.type) {
            ItemType.CHECKBOX -> {
                checkBox.visibility = View.VISIBLE
                imageView.visibility = View.GONE
                textView.visibility = View.VISIBLE
                textView.text = item.text
                checkBox.isChecked = item.isChecked
            }
            ItemType.IMAGEVIEW -> {
                checkBox.visibility = View.GONE
                imageView.visibility = View.VISIBLE
                textView.visibility = View.VISIBLE
                textView.text = item.text
            }
            ItemType.TEXTVIEW -> {
                checkBox.visibility = View.GONE
                imageView.visibility = View.GONE
                textView.visibility = View.VISIBLE
                textView.text = item.text
            }
        }

        // Handle double-tap to prompt edit or delete
        view.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0
            override fun onClick(v: View) {
                val clickTime = System.currentTimeMillis()
                if (clickTime - lastClickTime < 300) { // Double tap detected
                    showEditDeleteDialog(position)
                }
                lastClickTime = clickTime
            }
        })

        return view
    }

    private fun showEditDeleteDialog(position: Int) {
        val item = items[position]
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit or Delete Item")
        builder.setMessage("What do you want to do with this item?")

        builder.setPositiveButton("Edit") { _, _ ->
            // Logic to edit the item
            val input = EditText(context)
            input.setText(item.text)
            AlertDialog.Builder(context)
                .setTitle("Edit Item")
                .setView(input)
                .setPositiveButton("OK") { _, _ ->
                    item.text = input.text.toString()
                    notifyDataSetChanged()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        builder.setNegativeButton("Delete") { _, _ ->
            // Remove the item
            items.removeAt(position)
            notifyDataSetChanged()
        }

        builder.setNeutralButton("Cancel", null)
        builder.show()
    }
}
