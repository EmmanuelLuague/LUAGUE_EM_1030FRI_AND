package com.example.luaguemenu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.my_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Luague Menu"
        supportActionBar?.subtitle = "9/20/2024"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_first -> {
                loadFragment(AnotherFragment())
                true
            }
            R.id.menu_second -> {
                MyDialog().show(supportFragmentManager, "MyDialog")
                true
            }
            R.id.submenu_exit -> {
                true
            }
            R.id.exit_confirmation -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    class MyDialog : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
            return AlertDialog.Builder(requireActivity())
                .setTitle("Congratulations!")
                .setMessage("If you can see this you are amazing!")
                .setPositiveButton("OK") { _, _ ->
                }
                .setNegativeButton("Cancel") { _, _ ->
                }
                .create()
        }
    }
}
