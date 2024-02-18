package com.example.composepractice

class MainRepository {
    private val items = mutableListOf<Item>()

    init {
        items.add(Item("Item 1"))
        items.add(Item("Item 2"))
        items.add(Item("Item 3"))
    }

    fun getItem(): MutableList<Item> = items.toMutableList()

    fun addItem(item: Item) {
        items.add(item)
    }
}
