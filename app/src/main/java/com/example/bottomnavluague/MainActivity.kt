package com.example.bottomnavluague

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private var currentTabId: Int = R.id.nav_list // Default to the 2nd tab (List)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Restore the current tab if there's a saved instance
        if (savedInstanceState != null) {
            currentTabId = savedInstanceState.getInt("currentTabId", R.id.nav_list)
        }

        bottomNav = findViewById(R.id.bottomNavigationView)

        // Load the default or saved tab fragment
        loadFragment(getFragmentByTabId(currentTabId))

        // Set up the BottomNavigationView
        bottomNav.setOnNavigationItemSelectedListener { menuItem ->
            currentTabId = menuItem.itemId
            loadFragment(getFragmentByTabId(currentTabId))
            true
        }

        // Ensure the current tab is selected after orientation change
        bottomNav.selectedItemId = currentTabId
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentTabId", currentTabId) // Save the current tab ID
    }

    private fun getFragmentByTabId(tabId: Int): Fragment {
        return when (tabId) {
            R.id.nav_calculator -> CalculatorFragment()
            R.id.nav_list -> ListFragment()
            R.id.nav_profile -> ProfileFragment()
            else -> ListFragment() // Default to ListFragment
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}
