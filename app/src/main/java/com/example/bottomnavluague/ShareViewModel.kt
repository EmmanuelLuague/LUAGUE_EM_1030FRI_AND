package com.example.bottomnavluague

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _listItems = MutableLiveData<MutableList<ListItem>>(mutableListOf())
    val listItems: LiveData<MutableList<ListItem>> = _listItems
    val calculatorResult = MutableLiveData<Double>()

    // Add an item to the list and notify observers
    fun addItem(item: ListItem) {
        _listItems.value?.add(item)
        _listItems.value = _listItems.value
    }
}
