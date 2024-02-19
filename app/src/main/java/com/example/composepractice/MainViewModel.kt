package com.example.composepractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(mutableListOf())
    val items: StateFlow<List<Item>> get() = _items

    private val _itemsLiveData = MutableLiveData<List<Item>>(mutableListOf())
    val itemsLiveData: MutableLiveData<List<Item>> get() = _itemsLiveData

    init {
        fetchItems()
    }

    private fun fetchItems() {
        _items.value = repository.getItem()
//        _itemsLiveData.value = repository.getItem()
        _itemsLiveData.postValue(repository.getItem())
        _itemsLiveData
    }

    fun addItem() {
        repository.addItem()
        fetchItems()
    }

    fun deleteItem(idx: Int) {
        repository.deleteItem(idx)
        fetchItems()
    }

    fun checkedItem(value: Boolean, index: Int) {
        repository.checkedItem(value, index)
        fetchItems()
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MainViewModel(
                    MainRepository()
                )
            }
        }
    }
}